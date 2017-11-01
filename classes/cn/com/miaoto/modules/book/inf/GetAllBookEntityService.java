package cn.com.miaoto.modules.book.inf;

import cn.com.miaoto.modules.book.model.GetAllBookEntityReq;
import cn.com.miaoto.modules.book.model.GetAllBookEntityResp;

/**
 * Created by hx on 2017/7/27.
 */
public interface GetAllBookEntityService {

    GetAllBookEntityResp getAllBookEntity(GetAllBookEntityReq reqBean, GetAllBookEntityResp respBean);

}
