package cn.com.miaoto.modules.book.inf;

import cn.com.miaoto.modules.book.model.UpdateBookStatusReq;
import cn.com.miaoto.modules.book.model.UpdateBookStatusResp;

public interface UpdateBookStatusService {

	UpdateBookStatusResp changeBookEntityStatus(UpdateBookStatusReq reqBean, UpdateBookStatusResp respBean);

}
