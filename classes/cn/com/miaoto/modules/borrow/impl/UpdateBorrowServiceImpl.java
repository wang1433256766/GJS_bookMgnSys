package cn.com.miaoto.modules.borrow.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BorrowDao;
import cn.com.miaoto.modules.borrow.inf.UpdateBorrowService;
import cn.com.miaoto.modules.borrow.model.UpdateBorrowReq;
import cn.com.miaoto.modules.borrow.model.UpdateBorrowResp;

@Service
public class UpdateBorrowServiceImpl extends AbstractService<UpdateBorrowReq, UpdateBorrowResp> implements UpdateBorrowService {

	@Resource
	public BorrowDao borrowDao;

	@Override
	public UpdateBorrowResp updateBorrow(UpdateBorrowReq reqBean, UpdateBorrowResp respBean) {
		return (UpdateBorrowResp) super.execute(reqBean, respBean);
	}

	@Override
	public boolean checkInput(UpdateBorrowReq reqBean) {
		return true;
	}

	@Override
	protected void handle(UpdateBorrowReq reqBean, UpdateBorrowResp respBean) throws Exception {
		int effected = borrowDao.updateBorrow(reqBean.getBorrow());
		if (effected == 1) {
			respBean.setResult(1);
		} else {
			respBean.setResult(0);
		}
	}
}
