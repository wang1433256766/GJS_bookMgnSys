package cn.com.miaoto.modules.book.inf;

import cn.com.miaoto.modules.book.model.FuzzySearchReq;
import cn.com.miaoto.modules.book.model.FuzzySearchResp;

/**
 * Created by hx on 2017/8/31.
 */
public interface FuzzySearchService {
    FuzzySearchResp fuzzySearch(FuzzySearchReq reqBean, FuzzySearchResp respBean);
}
