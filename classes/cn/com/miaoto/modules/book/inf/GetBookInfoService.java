package cn.com.miaoto.modules.book.inf;

import cn.com.miaoto.modules.book.model.GetBookInfoReq;
import cn.com.miaoto.modules.book.model.GetBookInfoResp;

public interface GetBookInfoService {

	GetBookInfoResp getBookInfoBycode(GetBookInfoReq reqBean, GetBookInfoResp respBean);

}
