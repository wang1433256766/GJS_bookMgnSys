package cn.com.miaoto.modules.borrow.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BorrowDao;
import cn.com.miaoto.modules.borrow.inf.QueryBorrowOfUserService;
import cn.com.miaoto.modules.borrow.model.QueryBorrowOfUserReq;
import cn.com.miaoto.modules.borrow.model.QueryBorrowOfUserResp;
import cn.com.miaoto.pojo.Borrow;

@Service
public class QueryBorrowOfUserServiceImpl extends AbstractService<QueryBorrowOfUserReq, QueryBorrowOfUserResp> implements QueryBorrowOfUserService {

	@Resource
	private BorrowDao borrowDao;

	@Override
	public QueryBorrowOfUserResp queryBorrowedOfUser(QueryBorrowOfUserReq reqBean, QueryBorrowOfUserResp respBean) {
		return (QueryBorrowOfUserResp) super.execute(reqBean, respBean);
	}

	@Override
	public boolean checkInput(QueryBorrowOfUserReq reqBean) {
		return true;
	}

	@Override
	protected void handle(QueryBorrowOfUserReq reqBean, QueryBorrowOfUserResp respBean) throws Exception {
		Borrow borrow = borrowDao.selectBorrowedByBook(reqBean.getBarCode());
		if (borrow == null) {
			LOGGER.error("book is unavailable, but cant find the borrow record");
			respBean.setBorrow(null);
		}
		respBean.setBorrow(borrow);
	}
}
