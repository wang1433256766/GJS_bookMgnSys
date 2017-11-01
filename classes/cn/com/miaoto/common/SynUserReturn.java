package cn.com.miaoto.common;

import cn.com.miaoto.pojo.Borrow;
import cn.com.miaoto.pojo.UserInfo;
import cn.com.miaoto.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SynUserReturn
 * Created by hx on 2017/9/11.
 */
@Component
public class SynUserReturn {

    public static final Logger LOGGER = LoggerFactory.getLogger(SynUserReturn.class);

    private int synStatus;

    private String updatetime;

    private List<Borrow> borrowList = new ArrayList<>();

    private List<UserInfo> addUserList = new ArrayList<>();

    private List<UserInfo> deleteUserList = new ArrayList<>();

    private List<UserInfo> updateUserList = new ArrayList<>();

    public int getSynStatus() {
        return synStatus;
    }

    public void setSynStatus(int synStatus) {
        LOGGER.trace("update synStatus : ", synStatus);
        this.synStatus = synStatus;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public List<Borrow> getBorrowList() {
        return borrowList;
    }

    public void setBorrowList(List<Borrow> borrowList) {
        this.borrowList = borrowList;
    }

    public List<UserInfo> getAddUserList() {
        return addUserList;
    }

    public void setAddUserList(List<UserInfo> addUserList) {
        this.addUserList = addUserList;
    }

    public List<UserInfo> getDeleteUserList() {
        return deleteUserList;
    }

    public void setDeleteUserList(List<UserInfo> deleteUserList) {
        this.deleteUserList = deleteUserList;
    }

    public List<UserInfo> getUpdateUserList() {
        return updateUserList;
    }

    public void setUpdateUserList(List<UserInfo> updateUserList) {
        this.updateUserList = updateUserList;
    }

    public void addBorrow(Borrow borrow) {
        borrowList.add(borrow);
    }

    public void addAddUser(UserInfo userInfo) {
        addUserList.add(userInfo);
    }

    public void addDeleteUser(UserInfo userInfo) {
        deleteUserList.add(userInfo);
    }

    public void addUpdateUser(UserInfo userInfo) {
        updateUserList.add(userInfo);
    }

    public void addBorrowList(List<Borrow> borrowList) {
        borrowList.addAll(borrowList);
    }

    public void clear() {
        borrowList.clear();
        addUserList.clear();
        deleteUserList.clear();
        updateUserList.clear();

        synStatus = 0;
        updatetime = TimeUtil.getCurrentTimeStr();
    }

    @Override
    public String toString() {
        return "SynUserReturn{" +
                "borrowList=" + Arrays.asList(borrowList).toString() +
                ", addUserList=" + Arrays.asList(addUserList).toString() +
                ", deleteUserList=" + Arrays.asList(deleteUserList).toString() +
                ", updateUserList=" + Arrays.asList(updateUserList).toString() +
                '}';
    }
}
