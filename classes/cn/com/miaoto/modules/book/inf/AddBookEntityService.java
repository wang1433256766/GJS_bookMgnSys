package cn.com.miaoto.modules.book.inf;

import cn.com.miaoto.modules.book.model.AddBookEntityReq;
import cn.com.miaoto.modules.book.model.AddBookEntityResp;

public interface AddBookEntityService {

	AddBookEntityResp addBookEntity(AddBookEntityReq reqBean, AddBookEntityResp respBean);

}
