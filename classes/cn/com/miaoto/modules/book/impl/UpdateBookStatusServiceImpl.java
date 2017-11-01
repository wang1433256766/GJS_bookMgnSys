package cn.com.miaoto.modules.book.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.modules.book.inf.UpdateBookStatusService;
import cn.com.miaoto.modules.book.model.UpdateBookStatusReq;
import cn.com.miaoto.modules.book.model.UpdateBookStatusResp;

@Service
public class UpdateBookStatusServiceImpl extends AbstractService<UpdateBookStatusReq, UpdateBookStatusResp> implements UpdateBookStatusService {

	@Resource
	BookEntityDao bookEntityDao;

	@Override
	public UpdateBookStatusResp changeBookEntityStatus(UpdateBookStatusReq reqBean, UpdateBookStatusResp respBean) {
		return (UpdateBookStatusResp) super.execute(reqBean, respBean);
	}

	@Override
	public boolean checkInput(UpdateBookStatusReq reqBean) {
		return true;
	}

	@Override
	protected void handle(UpdateBookStatusReq reqBean, UpdateBookStatusResp respBean) throws Exception {
		int effected = bookEntityDao.updateBookStatus(reqBean.getBarCode(), reqBean.getStatus());
		if (effected == 0) {
			respBean.setResult(0);
		}
		respBean.setResult(1);
	}
}
