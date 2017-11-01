package cn.com.miaoto.modules.book.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.miaoto.common.SiomConstants;
import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.dao.inf.BorrowDao;
import cn.com.miaoto.modules.book.inf.BackBookService;
import cn.com.miaoto.modules.book.model.BackBookReq;
import cn.com.miaoto.modules.book.model.BackBookResp;
import cn.com.miaoto.util.SessionUtil;
import cn.com.miaoto.util.TimeUtil;

@Service
public class BackBookServiceImpl extends AbstractService<BackBookReq, BackBookResp> implements BackBookService {

	public static final Logger LOGGER = LoggerFactory.getLogger(BackBookServiceImpl.class);

	@Resource
	BorrowDao borrowDao;

	@Resource
	BookEntityDao bookEntityDao;

	@Override
	public BackBookResp backBook(BackBookReq reqBean, BackBookResp respBean) {
		return (BackBookResp) super.execute(reqBean, respBean);
	}

	@Override
	public boolean checkInput(BackBookReq reqBean) {
		return true;
	}

	@Override
	protected void handle(BackBookReq reqBean, BackBookResp respBean) throws Exception {
		int backUid = (int) SessionUtil.getSessionValue("uid");
		if (backUid == 0) {
			LOGGER.error("get back admin id failed");
			return;
		}
		int effected = borrowDao.backBook(reqBean.getBarCode(), TimeUtil.getCurrentTimeStr(), backUid);
		if (effected == 0) {
			LOGGER.error("update borrowed table failed");
			return;
		}
		int effected2 = bookEntityDao.updateBookStatus(reqBean.getBarCode(), SiomConstants.BOOKSTATUS_AVAILABLE);
		if (effected2 == 0) {
			LOGGER.error("update book entity table failed");
			return;
		}
		respBean.setResult(1);
	}
}
