package cn.com.miaoto.modules.follow.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.FollowDao;
import cn.com.miaoto.modules.follow.inf.GetFollowService;
import cn.com.miaoto.modules.follow.model.GetFollowReq;
import cn.com.miaoto.modules.follow.model.GetFollowResp;
import cn.com.miaoto.pojo.Follow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hx on 2017/8/25.
 */
@Service
public class GetFollowServiceImpl extends AbstractService<GetFollowReq, GetFollowResp> implements GetFollowService {
    public static final Logger LOGGER = LoggerFactory.getLogger(GetFollowServiceImpl.class);

    @Resource
    FollowDao followDao;

    @Override
    public GetFollowResp getFollow(GetFollowReq reqBean, GetFollowResp respBean) {
        return (GetFollowResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetFollowReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetFollowReq reqBean, GetFollowResp respBean) throws Exception {
        List<Follow> followList = followDao.selectOwn(reqBean);
        if (followList == null) {
            LOGGER.error("get user follow failed");
            return;
        }
        respBean.setResultCode(1);
        respBean.setFollowList(followList);
    }
}
