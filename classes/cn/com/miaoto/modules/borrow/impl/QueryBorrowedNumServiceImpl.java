package cn.com.miaoto.modules.borrow.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BorrowDao;
import cn.com.miaoto.modules.borrow.inf.QueryBorrowedNumService;
import cn.com.miaoto.modules.borrow.model.QueryBorrowedNumReq;
import cn.com.miaoto.modules.borrow.model.QueryBorrowedNumResp;
import cn.com.miaoto.pojo.Borrow;

@Service
public class QueryBorrowedNumServiceImpl extends AbstractService<QueryBorrowedNumReq, QueryBorrowedNumResp> implements QueryBorrowedNumService {

	@Resource
	BorrowDao borrowDao;

	@Override
	public QueryBorrowedNumResp queryBorrowed(QueryBorrowedNumReq reqBean, QueryBorrowedNumResp respBean) {
		return (QueryBorrowedNumResp) super.execute(reqBean, respBean);
	}

	@Override
	public boolean checkInput(QueryBorrowedNumReq reqBean) {
		return true;
	}

	@Override
	protected void handle(QueryBorrowedNumReq reqBean, QueryBorrowedNumResp respBean) throws Exception {
		List<Borrow> borrowList = borrowDao.selectBorrowedByUser(reqBean.getUid());
		if (borrowList == null) {
			return;
		}
		respBean.setBorrowList(borrowList);
	}
}
