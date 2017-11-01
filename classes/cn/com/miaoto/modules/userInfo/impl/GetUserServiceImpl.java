package cn.com.miaoto.modules.userInfo.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.UserInfoDao;
import cn.com.miaoto.modules.userInfo.inf.GetUserService;
import cn.com.miaoto.modules.userInfo.model.GetUserInfoReq;
import cn.com.miaoto.modules.userInfo.model.GetUserInfoResp;
import cn.com.miaoto.pojo.UserInfo;

@Service
public class GetUserServiceImpl extends AbstractService<GetUserInfoReq, GetUserInfoResp> implements GetUserService {
	@Resource
	UserInfoDao userInfoDao;

	@Override
	public GetUserInfoResp getUserInfo(GetUserInfoReq reqBean, GetUserInfoResp respBean) {
		return (GetUserInfoResp) super.execute(reqBean, respBean);
	}

	@Override
	public boolean checkInput(GetUserInfoReq reqBean) {
		return true;
	}

	@Override
	protected void handle(GetUserInfoReq reqBean, GetUserInfoResp respBean) throws Exception {
		UserInfo userInfo = userInfoDao.queryUserInfo(reqBean.getUser());
		respBean.setUser(userInfo);
	}
}
