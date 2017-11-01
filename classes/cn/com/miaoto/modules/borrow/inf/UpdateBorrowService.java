package cn.com.miaoto.modules.borrow.inf;

import cn.com.miaoto.modules.borrow.model.UpdateBorrowReq;
import cn.com.miaoto.modules.borrow.model.UpdateBorrowResp;

public interface UpdateBorrowService {

	UpdateBorrowResp updateBorrow(UpdateBorrowReq reqBean, UpdateBorrowResp respBean);

}
