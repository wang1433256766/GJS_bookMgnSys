package cn.com.miaoto.common.mvcBean;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractService<Q extends RequestInfo, P extends ResponseInfo> {
    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);

    private String logTag;

    private P respBean;

    public P execute(Q reqBean, P respBean) {
        init();
        LOGGER.info("Request : {}, req = [ {} ]", logTag, reqBean);

        try {
            if (checkInput(reqBean)) {
                handle(reqBean, respBean);
            } else {
                throw new Exception(StringUtil.appendStr(logTag, " request check input param invalid"));
            }
        } catch (Exception e) {
            LOGGER.error("{} Exception: ", logTag, e);
        }
        return respBean;
    }

    protected void init() {
        logTag = getClass().getSimpleName();
    }

    protected boolean checkInput(Q reqBean) {
        return true;
    }

    protected abstract void handle(Q reqBean, P respBean) throws Exception;

    public P getRespBean() {
        return respBean;
    }

    public void setRespBean(P respBean) {
        this.respBean = respBean;
    }


}
