package cn.com.miaoto.modules.book.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.dao.inf.BorrowDao;
import cn.com.miaoto.modules.book.inf.UpdateBookEntityService;
import cn.com.miaoto.modules.book.model.UpdateBookEntityReq;
import cn.com.miaoto.modules.book.model.UpdateBookEntityResp;
import cn.com.miaoto.pojo.BookEntity;
import cn.com.miaoto.pojo.Borrow;
import cn.com.miaoto.util.SessionUtil;
import cn.com.miaoto.util.StringUtil;
import cn.com.miaoto.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UpdateBookEntityServiceImpl extends AbstractService<UpdateBookEntityReq, UpdateBookEntityResp> implements UpdateBookEntityService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UpdateBookServiceImpl.class);

    @Resource
    private BookEntityDao bookEntityDao;

    @Resource
    private BorrowDao borrowDao;

    @Override
    public UpdateBookEntityResp updateBook(UpdateBookEntityReq reqBean, UpdateBookEntityResp respBean) {
        return (UpdateBookEntityResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(UpdateBookEntityReq reqBean) {
        return true;
    }

    @Override
    protected void handle(UpdateBookEntityReq reqBean, UpdateBookEntityResp respBean) throws Exception {

        List<Borrow> borrowList = new ArrayList<>();

//        // 更新实体信息
//        if (!StringUtil.isEmpty(reqBean.getBookEntity().getBeidStr())) {
//            String[] beids = reqBean.getBookEntity().getBeidStr().split(",");
//            List<Integer> list = new ArrayList<>();
//            for (String str : beids) {
//                list.add(Integer.parseInt(str));
//            }
//            reqBean.getBookEntity().setBeidList(list);
//        }
//        int effected = bookEntityDao.updateBookEntity(reqBean.getBookEntity());
//        respBean.setResult(effected);

        if (!StringUtil.isEmpty(reqBean.getBookEntity().getBeidStr())) {
            // 批量操作
            String[] beids = reqBean.getBookEntity().getBeidStr().split(",");
            for (String str : beids) {
                if (reqBean.getBookEntity().getStatus() == null) {
                    // 没有跟新status
                    int effected = bookEntityDao.updateBookEntity(reqBean.getBookEntity());
                    if (effected == 1) {
                        respBean.setResult(effected);
                    } else {
                        LOGGER.error("update bookentity failed");
                    }
                } else {
                    // 更新了status
                    if (true) {
                        // 通过bid查询barcode
                        BookEntity queryBook = new BookEntity();
                        queryBook.setBeid(Long.parseLong(str));
                        List<BookEntity> bookEntityList = bookEntityDao.queryBookEntityByBar(queryBook);
                        if (bookEntityList == null || bookEntityList.size() == 0) {
                            LOGGER.error("query bookentity by beid failed");
                            return;
                        }
                        // 通过barcode查询借阅信息
                        BookEntity bookEntity = bookEntityList.get(0);
                        Borrow borrow = borrowDao.selectBorrowedByBook(bookEntity.getBarcode());

                        if (borrow == null) {
                            // 没有借阅信息
                            BookEntity bookEntity1 = reqBean.getBookEntity();
                            bookEntity1.setBeid(Long.parseLong(str));
                            int effected = bookEntityDao.updateBookEntity(bookEntity1);
                            if (effected == 1) {
                                respBean.setResult(effected);
                            } else {
                                LOGGER.error("update bookentity failed");
                            }
                        } else {
                            // 有借阅信息

                            //更新borrow表
                            Object uid = SessionUtil.getSessionValue("uid");
                            if (uid == null) {
                                LOGGER.error("query back book admin failed");
                                uid = 1;
                            }
                            int effected1 = borrowDao.backBook(bookEntity.getBarcode(), TimeUtil.getCurrentTimeStr(), (int) uid);
                            if (effected1 == 0) {
                                LOGGER.error("update borrow record when update bookentity status failed, barcode = {}", bookEntity.getBarcode());
                            } else {
                                borrowList.add(borrow);
                            }
                            // 更新实体
                            BookEntity bookEntity1 = reqBean.getBookEntity();
                            bookEntity1.setLoster(borrow.getUid());
                            bookEntity1.setBeid(Long.parseLong(str));
                            int effected = bookEntityDao.updateBookEntity(reqBean.getBookEntity());
                            if (effected == 1) {
                                respBean.setResult(effected);
                            } else {
                                LOGGER.error("update bookentity failed");
                            }
                        }
                    } else {
                        // 没有更新status为丢失损坏
                        BookEntity bookEntity1 = reqBean.getBookEntity();
                        bookEntity1.setBeid(Long.parseLong(str));
                        int effected = bookEntityDao.updateBookEntity(bookEntity1);
                        if (effected == 1) {
                            respBean.setResult(effected);
                        } else {
                            LOGGER.error("update bookentity failed");
                        }
                    }
                }
            }
            respBean.setBorrowList(borrowList);
        } else {
            // 判断status
            if (reqBean.getBookEntity().getStatus() == null) {
                // 没有更新status
                int effected = bookEntityDao.updateBookEntity(reqBean.getBookEntity());
                if (effected == 1) {
                    respBean.setResult(effected);
                } else {
                    LOGGER.error("update bookentity failed");
                }
            } else {
                // 更新了status且是丢失和损坏
                if (true) {
                    // 通过bid查询barcode
                    BookEntity queryBook = new BookEntity();
                    queryBook.setBeid(reqBean.getBookEntity().getBeid());
                    List<BookEntity> bookEntityList = bookEntityDao.queryBookEntityByBar(queryBook);
                    if (bookEntityList == null || bookEntityList.size() == 0) {
                        LOGGER.error("query bookentity by beid failed");
                        return;
                    }
                    // 通过barcode查询借阅信息
                    BookEntity bookEntity = bookEntityList.get(0);
                    Borrow borrow = borrowDao.selectBorrowedByBook(bookEntity.getBarcode());

                    if (borrow == null) {
                        // 没有借阅信息
                        int effected = bookEntityDao.updateBookEntity(reqBean.getBookEntity());
                        if (effected == 1) {
                            respBean.setResult(effected);
                        } else {
                            LOGGER.error("update bookentity failed");
                        }
                    } else {
                        // 有借阅信息
                        //更新borrow表
                        Object uid = SessionUtil.getSessionValue("uid");
                        if (uid == null) {
                            LOGGER.error("query back book admin failed");
                            uid = 1;
                        }
                        int effected1 = borrowDao.backBook(bookEntity.getBarcode(), TimeUtil.getCurrentTimeStr(), (int) uid);
                        if (effected1 == 0) {
                            LOGGER.error("update borrow record when update bookentity status failed, barcode = {}", bookEntity.getBarcode());
                        } else {
                            borrowList.add(borrow);
                        }
                        respBean.setBorrowList(borrowList);

                        // 更新实体信息
                        BookEntity bookEntity1 = reqBean.getBookEntity();
                        bookEntity1.setLoster(borrow.getUid());
                        int effected = bookEntityDao.updateBookEntity(reqBean.getBookEntity());
                        if (effected == 1) {
                            respBean.setResult(effected);
                        } else {
                            LOGGER.error("update bookentity failed");
                        }
                    }
                } else {
                    // 没有更新status为丢失损坏
                    int effected = bookEntityDao.updateBookEntity(reqBean.getBookEntity());
                    if (effected == 1) {
                        respBean.setResult(effected);
                    } else {
                        LOGGER.error("update bookentity failed");
                    }
                }
            }
        }
    }
}
