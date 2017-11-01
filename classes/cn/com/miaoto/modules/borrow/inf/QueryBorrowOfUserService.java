package cn.com.miaoto.modules.borrow.inf;

import cn.com.miaoto.modules.borrow.model.QueryBorrowOfUserReq;
import cn.com.miaoto.modules.borrow.model.QueryBorrowOfUserResp;

public interface QueryBorrowOfUserService {

	QueryBorrowOfUserResp queryBorrowedOfUser(QueryBorrowOfUserReq reqBean, QueryBorrowOfUserResp respBean);

}
