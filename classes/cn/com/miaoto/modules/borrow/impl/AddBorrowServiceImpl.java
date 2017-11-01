package cn.com.miaoto.modules.borrow.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.miaoto.common.SiomConstants;
import cn.com.miaoto.common.SystemSetting;
import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.dao.inf.BorrowDao;
import cn.com.miaoto.modules.borrow.inf.AddBorrowService;
import cn.com.miaoto.modules.borrow.model.AddBorrowReq;
import cn.com.miaoto.modules.borrow.model.AddBorrowResp;
import cn.com.miaoto.util.SessionUtil;
import cn.com.miaoto.util.TimeUtil;

@Service
public class AddBorrowServiceImpl extends AbstractService<AddBorrowReq, AddBorrowResp> implements AddBorrowService {
	@Resource
	BorrowDao borrowDao;

	@Resource
	BookEntityDao bookEntityDao;

	@Override
	public AddBorrowResp addBorrowRecord(AddBorrowReq reqBean, AddBorrowResp respBean) {
		return (AddBorrowResp) super.execute(reqBean, respBean);
	}

	@Override
	public boolean checkInput(AddBorrowReq reqBean) {
		return true;
	}

	@Override
	protected void handle(AddBorrowReq reqBean, AddBorrowResp respBean) throws Exception {
		// 设置借阅管理员
		Integer uid = (Integer) SessionUtil.getSessionValue("uid");
		if (uid == null) {
			LOGGER.error("borrow admin's id is null");
			return;
		}
		reqBean.getBorrow().setBorrow(uid);
		// 设置借书时间
		reqBean.getBorrow().setCreatetime(TimeUtil.getCurrentTimeStr());
		// 获取归还时间
		Integer borrowTime = SystemSetting.getValue(SiomConstants.SETTING_BORROWTIME, Integer.class);
		if (borrowTime == null) {
			LOGGER.error("borrow time is not definded, and set default value of 30(day)");
			borrowTime = new Integer("30");
		}
		reqBean.getBorrow().setBacktime(TimeUtil.addDayOfCurrent(borrowTime));
		// 添加记录
		int id = borrowDao.insert(reqBean.getBorrow());
		if (id != 0) {
			respBean.setId(id);
		}

		int effected = bookEntityDao.updateBookStatus(reqBean.getBorrow().getBeid(), reqBean.getBookStatus());
		if (effected == 1) {
			respBean.setResult(1);
		} else {
			respBean.setResult(0);
		}
	}
}
