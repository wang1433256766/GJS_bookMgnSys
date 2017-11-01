package cn.com.miaoto.modules.borrow.inf;

import cn.com.miaoto.modules.borrow.model.AddBorrowReq;
import cn.com.miaoto.modules.borrow.model.AddBorrowResp;

public interface AddBorrowService {

	AddBorrowResp addBorrowRecord(AddBorrowReq reqBean, AddBorrowResp respBean);

}
