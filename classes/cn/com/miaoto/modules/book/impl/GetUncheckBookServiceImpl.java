package cn.com.miaoto.modules.book.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.miaoto.common.SiomConstants;
import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.modules.book.inf.GetUncheckBookService;
import cn.com.miaoto.modules.book.model.GetUncheckBookReq;
import cn.com.miaoto.modules.book.model.GetUncheckBookResp;
import cn.com.miaoto.pojo.BookEntity;

@Service
public class GetUncheckBookServiceImpl extends AbstractService<GetUncheckBookReq, GetUncheckBookResp> implements GetUncheckBookService {
	public static final Logger LOGGER = LoggerFactory.getLogger(GetUncheckBookServiceImpl.class);

	@Resource
	BookEntityDao bookEntityDao;

	@Override
	public GetUncheckBookResp getUNcheckedBook(GetUncheckBookReq reqBean, GetUncheckBookResp respBean) {
		return (GetUncheckBookResp) super.execute(reqBean, respBean);
	}

	@Override
	public boolean checkInput(GetUncheckBookReq reqBean) {
		return true;
	}

	@Override
	protected void handle(GetUncheckBookReq reqBean, GetUncheckBookResp respBean) throws Exception {
		List<BookEntity> bookEntityList = bookEntityDao.selectUncheck(reqBean.getBatchId(), SiomConstants.BOOKSTATUS_UNCHECKED);
		if (bookEntityList == null) {
			LOGGER.error("get unchecked book failed");
			return;
		}
		respBean.setBookEntityList(bookEntityList);
	}
}
