package cn.com.miaoto.modules.book.impl;

import cn.com.miaoto.common.SiomConstants;
import cn.com.miaoto.common.SystemSetting;
import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookDao;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.modules.book.inf.AddBookService;
import cn.com.miaoto.modules.book.model.AddBookReq;
import cn.com.miaoto.modules.book.model.AddBookResp;
import cn.com.miaoto.pojo.Book;
import cn.com.miaoto.pojo.BookEntity;
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
public class AddBookServiceImpl extends AbstractService<AddBookReq, AddBookResp> implements AddBookService {
    public static final Logger LOGGER = LoggerFactory.getLogger(AddBookServiceImpl.class);

    @Resource
    BookEntityDao bookEntityDao;

    @Resource
    BookDao bookDao;

    @Override
    public AddBookResp addBook(AddBookReq reqBean, AddBookResp respBean) {
        return (AddBookResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(AddBookReq reqBean) {
        return true;
    }

    @Override
    protected void handle(AddBookReq reqBean, AddBookResp respBean) throws Exception {
        Book book = reqBean.getBook();
        // 设置时间
        book.setCreatetime(TimeUtil.getCurrentTimeStr());
        book.setUpdatetime(TimeUtil.getCurrentTimeStr());
        // 设置种次号
        String category = book.getCategoryThird();
//        if (StringUtil.isEmpty(category)) {
//            LOGGER.error("category is null");
//            return;
//        }
        if (!StringUtil.isEmpty(category)) {
            int typeNum = bookDao.selectMaxCategory(category);
            typeNum += 1;
            book.setTypeNum(typeNum);
            // 设置索书号
            book.setClaimNumber(category + "/" + typeNum);
        }


        // 设置bookid
        bookDao.insertBook(book);
        if (book.getBid() == 0) {
            LOGGER.error("insert book failed");
            return;
        }
        if (reqBean.getNumOfBook() > 1) {
            boolean isSuccess = true;
            List<Long> longList = new ArrayList<>();
            Long start = SystemSetting.getValue(SiomConstants.SETTING_FOREIGN_START, Long.class);
            //判断是否是外文书籍
            boolean isForeign = true;
            if (book.getLang().equals(SiomConstants.LANG_CN)) {
                isForeign = false;
            }
            long barcode = bookEntityDao.selectMaxBarcode2(isForeign, start);
            for (int i = 0; i < reqBean.getNumOfBook(); i++) {

                longList.add(barcode + 1);

                BookEntity bookEntity = reqBean.getBookEntity();
                if (bookEntity == null) {
                    LOGGER.error("bookentity is null");
                    return;
                }
                bookEntity.setBarcode(barcode + 1);
                bookEntity.setBid(book.getBid());
                // 设置状态
                bookEntity.setStatus(SiomConstants.BOOKSTATUS_UNCHECKED);
                // 设置编目员
                int bmUid = (int) SessionUtil.getSessionValue("uid");
                if (bmUid == 0) {
                    LOGGER.error("bm uid is null");
                    return;
                }
                bookEntity.setBmUid(bmUid);
                // 设置编目时间
                bookEntity.setBmTime(TimeUtil.getCurrentTimeStr());

                int effected = bookEntityDao.insertBookEntity(bookEntity);
                if (effected == 0) {
                    LOGGER.error("insert bookEntity failed");
                    isSuccess = false;
                }
                barcode++;
            }
            respBean.setBarcodeList(longList);
            if (isSuccess == true) {
                respBean.setResult(1);
                return;
            }
            respBean.setResult(0);
        } else {
            // 设置条形码
            Long start = SystemSetting.getValue(SiomConstants.SETTING_FOREIGN_START, Long.class);
            //判断是否是外文书籍
            boolean isForeign = true;
            if (book.getLang().equals(SiomConstants.LANG_CN)) {
                isForeign = false;
            }
            long barcode = bookEntityDao.selectMaxBarcode2(isForeign, start);
            respBean.getBarcodeList().add(barcode + 1);

            BookEntity bookEntity = reqBean.getBookEntity();
            if (bookEntity == null) {
                LOGGER.error("bookentity is null");
                return;
            }
            bookEntity.setBarcode(barcode + 1);
            bookEntity.setBid(book.getBid());
            // 设置状态
            bookEntity.setStatus(SiomConstants.BOOKSTATUS_UNCHECKED);
            // 设置编目员
            int bmUid = (int) SessionUtil.getSessionValue("uid");
            if (bmUid == 0) {
                LOGGER.error("bm uid is null");
                return;
            }
            bookEntity.setBmUid(bmUid);
            // 设置编目时间
            bookEntity.setBmTime(TimeUtil.getCurrentTimeStr());

            int effected = bookEntityDao.insertBookEntity(bookEntity);
            if (effected == 0) {
                LOGGER.error("insert bookEntity failed");
                return;
            }
            respBean.setResult(1);
        }

    }
}
