package cn.com.miaoto.modules.notify.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Notify;

import java.util.List;

/**
 * Created by hx on 2017/8/14.
 */
public class GetAllNotifyResp extends ResponseInfo {

    private List<Notify> notifyList;

    private int page;

    private int allrows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getAllrows() {
        return allrows;
    }

    public void setAllrows(int allrows) {
        this.allrows = allrows;
    }

    public List<Notify> getNotifyList() {
        return notifyList;
    }

    public void setNotifyList(List<Notify> notifyList) {
        this.notifyList = notifyList;
    }
}
