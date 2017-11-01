package cn.com.miaoto.modules.book.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookDao;
import cn.com.miaoto.modules.book.inf.GetClaimNumberService;
import cn.com.miaoto.modules.book.model.GetClaimNumberReq;
import cn.com.miaoto.modules.book.model.GetClaimNumberResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hx on 2017/9/4.
 */
@Service
public class GetClaimNumberServiceImpl extends AbstractService<GetClaimNumberReq, GetClaimNumberResp> implements GetClaimNumberService {
    public static final Logger LOGGER = LoggerFactory.getLogger(GetClaimNumberServiceImpl.class);

    @Resource
    BookDao bookDao;


    @Override
    public GetClaimNumberResp getClaimNumber(GetClaimNumberReq reqBean, GetClaimNumberResp respBean) {
        return (GetClaimNumberResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetClaimNumberReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetClaimNumberReq reqBean, GetClaimNumberResp respBean) throws Exception {
        int max = bookDao.selectMaxCategory(reqBean.getTypeNum());
        String clainNumber = reqBean.getTypeNum() + "/" + (max + 1);
        respBean.setClainNumber(clainNumber);
        respBean.setResultCode(1);
    }
}
