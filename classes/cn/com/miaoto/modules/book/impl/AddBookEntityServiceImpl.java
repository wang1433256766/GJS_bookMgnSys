package cn.com.miaoto.modules.book.impl;

import cn.com.miaoto.common.SiomConstants;
import cn.com.miaoto.common.SystemSetting;
import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.modules.book.inf.AddBookEntityService;
import cn.com.miaoto.modules.book.model.AddBookEntityReq;
import cn.com.miaoto.modules.book.model.AddBookEntityResp;
import cn.com.miaoto.pojo.BookEntity;
import cn.com.miaoto.util.SessionUtil;
import cn.com.miaoto.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AddBookEntityServiceImpl extends AbstractService<AddBookEntityReq, AddBookEntityResp> implements AddBookEntityService {
    public static final Logger LOGGER = LoggerFactory.getLogger(AddBookEntityServiceImpl.class);

    @Resource
    BookEntityDao bookEntityDao;


    @Override
    public AddBookEntityResp addBookEntity(AddBookEntityReq reqBean, AddBookEntityResp respBean) {
        return (AddBookEntityResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(AddBookEntityReq reqBean) {
        return true;
    }

    @Override
    protected void handle(AddBookEntityReq reqBean, AddBookEntityResp respBean) throws Exception {
        //查询书目是否是外文书籍
        boolean isForeign = reqBean.isForeign();

        // 设置条形码
        long barcode = bookEntityDao.selectMaxBarcode2(isForeign, SystemSetting.getValue(SiomConstants.SETTING_FOREIGN_START, Long.class));
        respBean.setBarcode(barcode + 1);
        BookEntity bookEntity = reqBean.getBookEntity();
        if (bookEntity == null) {
            LOGGER.error("bookentity is null");
            return;
        }
        bookEntity.setBarcode(barcode + 1);
        // 设置状态
        bookEntity.setStatus(SiomConstants.BOOKSTATUS_UNCHECKED);
        // 设置编目员
        int bmUid = (int) SessionUtil.getSessionValue("uid");
        if (bmUid == 0) {
            LOGGER.error("bm uid is null");
        }
        bookEntity.setBmUid(bmUid);
        // 设置编目时间
        bookEntity.setBmTime(TimeUtil.getCurrentTimeStr());

        int effected = bookEntityDao.insertBookEntity(bookEntity);
        if (effected == 0) {
            respBean.setResult(0);
        } else {
            respBean.setResult(1);
        }
    }
}
