package cn.com.miaoto.modules.follow.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.FollowDao;
import cn.com.miaoto.modules.follow.inf.AddFollowService;
import cn.com.miaoto.modules.follow.model.AddFollowReq;
import cn.com.miaoto.modules.follow.model.AddFollowResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hx on 2017/8/18.
 */
@Service
public class AddFollowServiceImpl extends AbstractService<AddFollowReq, AddFollowResp> implements AddFollowService {
    public static final Logger LOGGER = LoggerFactory.getLogger(AddFollowServiceImpl.class);

    @Resource
    FollowDao followDao;

    @Override
    public AddFollowResp addFollow(AddFollowReq reqBean, AddFollowResp respBean) {
        return (AddFollowResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(AddFollowReq reqBean) {
        return true;
    }

    @Override
    protected void handle(AddFollowReq reqBean, AddFollowResp respBean) throws Exception {
        int effected = followDao.insert(reqBean.getFollow());
        if(effected == 0) {
            LOGGER.error("add follow failed");
            respBean.setResultCode(0);
        }
        respBean.setResultCode(1);
    }
}
