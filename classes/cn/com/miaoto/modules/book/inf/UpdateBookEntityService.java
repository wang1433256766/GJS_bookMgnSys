package cn.com.miaoto.modules.book.inf;

import cn.com.miaoto.modules.book.model.UpdateBookEntityReq;
import cn.com.miaoto.modules.book.model.UpdateBookEntityResp;

public interface UpdateBookEntityService {

	UpdateBookEntityResp updateBook(UpdateBookEntityReq reqBean, UpdateBookEntityResp respBean);

}
