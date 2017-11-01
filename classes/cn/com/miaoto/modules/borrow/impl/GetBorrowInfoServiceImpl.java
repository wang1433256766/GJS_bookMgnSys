package cn.com.miaoto.modules.borrow.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.dao.inf.BorrowDao;
import cn.com.miaoto.modules.borrow.inf.GetBorrowInfoService;
import cn.com.miaoto.modules.borrow.model.GetBorrowInfoReq;
import cn.com.miaoto.modules.borrow.model.GetBorrowInfoResp;
import cn.com.miaoto.pojo.Borrow;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hx on 2017/8/25.
 */
@Service
public class GetBorrowInfoServiceImpl extends AbstractService<GetBorrowInfoReq, GetBorrowInfoResp> implements GetBorrowInfoService {
    @Resource
    BorrowDao borrowDao;

    @Resource
    BookEntityDao bookEntityDao;

    @Override
    public GetBorrowInfoResp getBorrowInfoRecord(GetBorrowInfoReq reqBean, GetBorrowInfoResp respBean) {
        return (GetBorrowInfoResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetBorrowInfoReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetBorrowInfoReq reqBean, GetBorrowInfoResp respBean) throws Exception {
        List<Borrow> borrowList = borrowDao.selectAll(reqBean);
        if (borrowList == null) {
            LOGGER.error("get user's all borrow failed");
            return;
        }

        int rows = borrowDao.count(reqBean.getUid());

        respBean.setAllRows(rows);
        respBean.setResultCode(1);
        respBean.setBorrowList(borrowList);
    }
}
