package cn.com.miaoto.modules.book.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookDao;
import cn.com.miaoto.modules.book.inf.UpdateBookService;
import cn.com.miaoto.modules.book.model.UpdateBookReq;
import cn.com.miaoto.modules.book.model.UpdateBookResp;
import cn.com.miaoto.pojo.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UpdateBookServiceImpl extends AbstractService<UpdateBookReq, UpdateBookResp> implements UpdateBookService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UpdateBookServiceImpl.class);

    @Resource
    BookDao bookDao;

    @Override
    public UpdateBookResp updateBook(UpdateBookReq reqBean, UpdateBookResp respBean) {
        return (UpdateBookResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(UpdateBookReq reqBean) {
        return true;
    }

    @Override
    protected void handle(UpdateBookReq reqBean, UpdateBookResp respBean) throws Exception {

        Book book = reqBean.getBook();

        String claimNum = reqBean.getBook().getClaimNumber();

        String catagory2 = "";
        int typeNum = 0;
        String typeNumAdd = "";

        if (claimNum != null && !claimNum.equals("")) {
            int first = claimNum.indexOf("/");
            if (first != -1) {
                // 设置catagory2
                catagory2 = claimNum.substring(0, first);

                String tmp = claimNum.substring(first + 1);

                //索书号补充分割
                int start1 = tmp.indexOf("/");
                int start2 = tmp.indexOf("(");
                int start3 = tmp.indexOf("（");
                if (start1 != -1) {
                    typeNum = Integer.parseInt(tmp.substring(0, start1));
                    typeNumAdd = "/" + tmp.substring(start1 + 1);
                } else {
                    if (start2 != -1) {
                        try {
                            typeNum = Integer.parseInt(tmp.substring(0, start2));
                        } catch (Exception e) {
                            LOGGER.error("format typeNum failed, typeNum = {}", tmp.substring(0, start2));
                        }
                        typeNumAdd = "(" + tmp.substring(start2 + 1);
                    } else {
                        if (start3 != -1) {
                            try {
                                typeNum = Integer.parseInt(tmp.substring(0, start3));

                            } catch (Exception e) {
                                LOGGER.error("format typeNum failed, typeNum = {}", tmp.substring(0, start3));
                            }
                            typeNumAdd = "（" + tmp.substring(start3 + 1);
                        } else {
                            try {
                                if (!tmp.equals("")) {
                                    typeNum = Integer.parseInt(tmp);
                                } else {
                                    typeNum = 0;
                                }
                                typeNumAdd = "";
                            } catch (Exception e) {
                                LOGGER.error("resolve claimNumber failed");
                            }

                        }
                    }
                }
            }
            book.setCategory2(catagory2);
            book.setTypeNum(typeNum);
            book.setTypeNumAdd(typeNumAdd);
        }


        int effected = bookDao.updateBook(book);
        if (effected == 1) {
            respBean.setResultCode(1);
        }
    }
}
