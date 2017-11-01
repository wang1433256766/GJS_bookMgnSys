package cn.com.miaoto.modules.follow.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.FollowDao;
import cn.com.miaoto.modules.follow.inf.UpdateFollowService;
import cn.com.miaoto.modules.follow.model.UpdateFollowReq;
import cn.com.miaoto.modules.follow.model.UpdateFollowResp;
import cn.com.miaoto.pojo.Follow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hx on 2017/8/25.
 */
@Service
public class UpdateFollowServiceImpl extends AbstractService<UpdateFollowReq, UpdateFollowResp> implements UpdateFollowService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UpdateFollowServiceImpl.class);

    @Resource
    FollowDao followDao;

    @Override
    public UpdateFollowResp updateFollow(UpdateFollowReq reqBean, UpdateFollowResp respBean) {
        return (UpdateFollowResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(UpdateFollowReq reqBean) {
        return true;
    }

    @Override
    protected void handle(UpdateFollowReq reqBean, UpdateFollowResp respBean) throws Exception {
        Follow follow = new Follow();
        follow.setFoID(reqBean.getFoid());
        follow.setFoStatus(reqBean.getStatus());

        int effected = followDao.updateStatus(follow);
        if (effected == 0) {
            LOGGER.error("update follow status failed");
            return;
        }
        respBean.setResultCode(1);
    }
}
