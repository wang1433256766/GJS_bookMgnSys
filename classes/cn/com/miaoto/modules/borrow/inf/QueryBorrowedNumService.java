package cn.com.miaoto.modules.borrow.inf;

import cn.com.miaoto.modules.borrow.model.QueryBorrowedNumReq;
import cn.com.miaoto.modules.borrow.model.QueryBorrowedNumResp;

public interface QueryBorrowedNumService {

	QueryBorrowedNumResp queryBorrowed(QueryBorrowedNumReq reqBean, QueryBorrowedNumResp respBean);

}
