package cn.com.miaoto.controller;

import cn.com.miaoto.common.BookStats;
import cn.com.miaoto.common.ReturnMsg;
import cn.com.miaoto.common.SiomConstants;
import cn.com.miaoto.common.SystemSetting;
import cn.com.miaoto.common.mvcBean.BaseController;
import cn.com.miaoto.modules.book.inf.*;
import cn.com.miaoto.modules.book.model.*;
import cn.com.miaoto.modules.borrow.inf.*;
import cn.com.miaoto.modules.borrow.model.*;
import cn.com.miaoto.modules.userInfo.inf.GetUserService;
import cn.com.miaoto.modules.userInfo.model.GetUserInfoReq;
import cn.com.miaoto.modules.userInfo.model.GetUserInfoResp;
import cn.com.miaoto.pojo.Borrow;
import cn.com.miaoto.pojo.UserInfo;
import cn.com.miaoto.pojo.common.BackReturn;
import cn.com.miaoto.pojo.common.BorrowReturn;
import cn.com.miaoto.pojo.common.SearchFilter;
import cn.com.miaoto.util.SessionUtil;
import cn.com.miaoto.util.StringUtil;
import cn.com.miaoto.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 借阅相关controller
 */
@Controller
@RequestMapping("/")
@DependsOn({"bookStats"})
public class BorrowController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(BorrowController.class);

    @Resource
    private GetUserBorrowedBookService getUserBorrowedBookService;

    @Resource
    private AddBorrowService addBorrowService;

    @Resource
    private GetUserService getUserService;

    @Resource
    private GetBookInfoService getBookInfoService;

    @Resource
    private UpdateBookStatusService updateBookStatusService;

    @Resource
    private QueryBorrowedNumService queryBorrowedNumService;

    @Resource
    private BackBookService backBookService;

    @Resource
    private QueryBorrowOfUserService queryBorrowOfUserService;

    @Resource
    private UpdateBorrowService updateBorrowService;

    @Resource
    private GetBookInfoByBidService getBookInfoByBidService;

    @Resource
    private GetBorrowInfoService getBorrowInfoService;

    @Resource
    private BookStats bookStats;


    /**
     * 借阅
     *
     * @param borrow 借阅信息
     * @return ReturnMsg
     */
    @RequestMapping(value = "/borrow", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg borrow(Borrow borrow) {
        ReturnMsg msg = new ReturnMsg();
        try {
            //设置return.barcode
            BorrowReturn borrowReturn = new BorrowReturn();
            borrowReturn.setBarcode(borrow.getBeid() + "");
            // 查询用户
            UserInfo user = new UserInfo(borrow.getCard());
            GetUserInfoReq reqBean = new GetUserInfoReq(user);
            GetUserInfoResp respBean = new GetUserInfoResp();
            respBean = getUserService.getUserInfo(reqBean, respBean);
            if (respBean == null || respBean.getUser() == null) {
                msg.setMsg(2, "user is not exist", null);
                return msg;
            }

            // 判断用户状态
            if (respBean.getUser().getStatus() != 0) {
                msg.setMsg(3, "user cant borrow any book because of user status", null);
                return msg;
            }

            // 判断图书状态
            GetBookInfoReq reqBean3 = new GetBookInfoReq(borrow.getBeid() + "");
            GetBookInfoResp respBean3 = new GetBookInfoResp();
            respBean3 = getBookInfoService.getBookInfoBycode(reqBean3, respBean3);


            // 触发借阅统计
            bookStats.addBookBorrow(respBean3.getBookEntity().getBid());

            //设置return.bookname和bookstatus
            GetBookInfoByBidReq reqBean8 = new GetBookInfoByBidReq(respBean3.getBookEntity().getBid());
            GetBookInfoByBidResp respBean8 = new GetBookInfoByBidResp();
            respBean8 = getBookInfoByBidService.getBookInfoByBid(reqBean8, respBean8);
            borrowReturn.setBookName(respBean8.getBook().getName());
            borrowReturn.setBookStatus(respBean3.getBookEntity().getStatus());

            if (respBean3.getBookEntity() == null) {
                msg.setMsg(1, "cant find this book", borrowReturn);
                return msg;
            }


            //borrow
            if (respBean3.getBookEntity().getStatus() != SiomConstants.BOOKSTATUS_AVAILABLE) {
                // 已预约
                if (respBean3.getBookEntity().getStatus() == SiomConstants.BOOKSTATUS_UNAVAILABLE) {
                    // 判断是否是预约人
                    QueryBorrowOfUserReq reqBean6 = new QueryBorrowOfUserReq(borrow.getBeid());
                    QueryBorrowOfUserResp respBean6 = new QueryBorrowOfUserResp();
                    if (queryBorrowOfUserService.queryBorrowedOfUser(reqBean6, respBean6).getBorrow().getUid() == respBean.getUser().getUid()) {
                        UpdateBookStatusReq reqBean7 = new UpdateBookStatusReq(SiomConstants.BOOKSTATUS_BORROWED, borrow.getBeid());
                        UpdateBookStatusResp respBean7 = new UpdateBookStatusResp();
                        updateBookStatusService.changeBookEntityStatus(reqBean7, respBean7);
                        if (respBean7.getResult() == 0) {
                            // 预约取书失败
                            msg.setMsg(4, "reserve book borrowed failed", borrowReturn);
                            return msg;
                        } else {
                            // 预约取书成功
                            msg.setMsg(0, "reserve book borrowed success", borrowReturn);
                            return msg;
                        }
                    } else {
                        // 不是预约人
                        msg.setMsg(4, "this book is reserve, status is " + respBean3.getBookEntity().getStatus(), borrowReturn);
                        return msg;
                    }
                } else {
                    msg.setMsg(5, "this book cant be borrowed, status is " + respBean3.getBookEntity().getStatus(), borrowReturn);
                    return msg;
                }
            }
            // 判断是否达到最大可借书目
            QueryBorrowedNumReq reqBean5 = new QueryBorrowedNumReq(respBean.getUser().getUid());
            QueryBorrowedNumResp respBean5 = new QueryBorrowedNumResp();
            respBean5 = queryBorrowedNumService.queryBorrowed(reqBean5, respBean5);
            if (respBean5 != null && respBean5.getBorrowList().size() >= SystemSetting.getValue(SiomConstants.SETTING_MAXBORROWED, Integer.class)) {
                msg.setMsg(6, "count of borrowed book get max", borrowReturn);
                return msg;
            }

            // 设置uid
            borrow.setUid(respBean.getUser().getUid());
            // 添加借书记录
            AddBorrowReq reqBean2 = new AddBorrowReq(borrow);
            AddBorrowResp respBean2 = new AddBorrowResp();
            reqBean2.setBookStatus(SiomConstants.BOOKSTATUS_BORROWED);
            respBean2 = addBorrowService.addBorrowRecord(reqBean2, respBean2);
            if (respBean2 == null || respBean2.getResult() == 0) {
                msg.setMsg(7, "add borrow record failed", borrowReturn);
                return msg;
            }
            //设置return

            borrowReturn.setBookStatus(respBean3.getBookEntity().getStatus());

            msg.setMsg(0, "borrow success", borrowReturn);
            return msg;
        } catch (Exception e) {
            LOGGER.error("BorrowController borrow() catch exception : ", e);
            msg.setMsg(4, "add borrow record failed", null);
            return msg;
        }
    }

    /**
     * 还书
     *
     * @param barCode
     * @return
     */
    @RequestMapping(value = "back", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg back(long barCode) {
        ReturnMsg msg = new ReturnMsg();
        try {
            BackReturn backReturn = new BackReturn();
            //get borrow info of user
            QueryBorrowOfUserReq reqBean2 = new QueryBorrowOfUserReq(barCode);
            QueryBorrowOfUserResp respBean2 = new QueryBorrowOfUserResp();
            respBean2 = queryBorrowOfUserService.queryBorrowedOfUser(reqBean2, respBean2);
            if (respBean2.getBorrow() == null) {
                LOGGER.error("get borrowed book of user failed");
                msg.setMsg(1, "get borrowed book of user failed", null);
                return msg;
            }
            //获取用户信息
            GetUserInfoReq reqBean3;
            GetUserInfoResp respBean3 = new GetUserInfoResp();
            UserInfo user = new UserInfo();
            user.setUid(respBean2.getBorrow().getUid());
            reqBean3 = new GetUserInfoReq(user);
            respBean3 = getUserService.getUserInfo(reqBean3, respBean3);

            //设置backReturn.userCard和userName和barcode和bookname
            backReturn.setUserCard(respBean3.getUser().getCard() + "");
            backReturn.setUserName(respBean3.getUser().getUname());
            backReturn.setBarcode(barCode + "");


            //back book
            BackBookReq reqBean = new BackBookReq(barCode);
            BackBookResp respBean = new BackBookResp();
            respBean = backBookService.backBook(reqBean, respBean);
            if (respBean.getResult() == 0) {
                msg.setMsg(1, "back book failed", null);
                return msg;
            }

            //get borrowed book
            GetUserBorrowedBookReq reqBean4 = new GetUserBorrowedBookReq(respBean2.getBorrow().getUid());
            GetUserBorrowedBookResp respBean4 = new GetUserBorrowedBookResp();
            reqBean4.setBackBookBeid(barCode);
            respBean4 = getUserBorrowedBookService.getBookInfoByUid(reqBean4, respBean4);

            //set user
            UserInfo setUser = respBean3.getUser();
            setUser = StringUtil.clearSensitive(setUser);
            respBean.setUserInfo(setUser);
            //set borrow
            respBean.setBorrowList(respBean4.getBorrows());
            //set BackReturn
            backReturn.setBookName(respBean4.getBackBook().getName());
            respBean.setBackReturn(backReturn);
            msg.setMsg(0, "back book success", respBean);
            return msg;
        } catch (Exception e) {
            LOGGER.error("BorrowController back() catch exception : ", e);
            msg.setMsg(1, "back book failed", null);
            return msg;
        }
    }

    @RequestMapping(value = "reserve", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg reserve(Borrow borrow) {
        ReturnMsg msg = new ReturnMsg();
        try {
            // 查询用户
            UserInfo user = new UserInfo();
            user.setUid((int) SessionUtil.getSessionValue("uid"));
            GetUserInfoReq reqBean = new GetUserInfoReq(user);
            GetUserInfoResp respBean = new GetUserInfoResp();
            respBean = getUserService.getUserInfo(reqBean, respBean);
            if (respBean == null || respBean.getUser() == null) {
                msg.setMsg(1, "user is not exist", null);
                return msg;
            }

            // 判断用户状态
            if (respBean.getUser().getStatus() != 0) {
                msg.setMsg(1, "user cant borrow any book because of status", null);
                return msg;
            }
            // 判断图书状态
            GetBookInfoReq reqBean3 = new GetBookInfoReq(borrow.getBeid() + "");
            GetBookInfoResp respBean3 = new GetBookInfoResp();
            respBean3 = getBookInfoService.getBookInfoBycode(reqBean3, respBean3);
            if (respBean3 == null || respBean3.getBookEntity() == null) {
                msg.setMsg(1, "cant find this book", null);
                return msg;
            }
            if (respBean3.getBookEntity().getStatus() != SiomConstants.BOOKSTATUS_AVAILABLE) {
                msg.setMsg(1, "this book cant be borrowed, status is " + respBean3.getBookEntity().getStatus(), null);
                return msg;
            }
            // 判断是否达到最大可借书目
            QueryBorrowedNumReq reqBean5 = new QueryBorrowedNumReq(respBean.getUser().getUid());
            QueryBorrowedNumResp respBean5 = new QueryBorrowedNumResp();
            respBean5 = queryBorrowedNumService.queryBorrowed(reqBean5, respBean5);
            if (respBean5 != null && respBean5.getBorrowList().size() >= SystemSetting.getValue(SiomConstants.SETTING_MAXBORROWED, Integer.class)) {
                msg.setMsg(1, "count of borrowed book get max", null);
                return msg;
            }

            // 设置uid
            borrow.setUid(respBean.getUser().getUid());
            // 添加借书记录
            AddBorrowReq reqBean2 = new AddBorrowReq(borrow);
            AddBorrowResp respBean2 = new AddBorrowResp();
            reqBean2.setBookStatus(SiomConstants.BOOKSTATUS_UNAVAILABLE);
            respBean2 = addBorrowService.addBorrowRecord(reqBean2, respBean2);
            if (respBean2 == null || respBean2.getResult() == 0) {
                msg.setMsg(1, "add borrow record failed", null);
                return msg;
            }

            msg.setMsg(0, "reserve success", null);
            return msg;
        } catch (Exception e) {
            LOGGER.error("BorrowController reserve() catch exception : ", e);
            msg.setMsg(1, "reserve failed", null);
            return msg;
        }
    }

    @RequestMapping(value = "renew", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg renew(long barCode) {
        ReturnMsg msg = new ReturnMsg();
        try {
            QueryBorrowOfUserReq reqBean = new QueryBorrowOfUserReq(barCode);
            QueryBorrowOfUserResp respBean = new QueryBorrowOfUserResp();
            respBean = queryBorrowOfUserService.queryBorrowedOfUser(reqBean, respBean);
            if (respBean.getBorrow() == null) {
                msg.setMsg(1, "get borrowed msg failed", null);
                return msg;
            }
            if (respBean.getBorrow().getUid() != (int) SessionUtil.getSessionValue("uid")) {
                msg.setMsg(1, "permission deny", null);
                return msg;
            }
            if (respBean.getBorrow().getRenewal() >= 3) {
                msg.setMsg(1, "renewal count get 3", null);
                return msg;
            }
            // 更新borrow信息
            Borrow borrow = new Borrow();
            borrow.setRenewal(respBean.getBorrow().getRenewal() + 1);
            borrow.setBeid(barCode);
            // 延长还书时间
            borrow.setBacktime(TimeUtil.addDayOfTime(respBean.getBorrow().getBacktime(), SystemSetting.getValue(SiomConstants.SETTING_RENEWPERIOD, Integer.class)));
            UpdateBorrowReq reqBean2 = new UpdateBorrowReq(borrow);
            UpdateBorrowResp respBean2 = new UpdateBorrowResp();
            respBean2 = updateBorrowService.updateBorrow(reqBean2, respBean2);
            if (respBean2.getResult() == 0) {
                msg.setMsg(1, "update borrow record failed", null);
                return msg;
            }

            msg.setMsg(0, "renewal success", null);
            return msg;
        } catch (Exception e) {
            LOGGER.error("BorrowController renew() catch exception : ", e);
            msg.setMsg(1, "renew failed", null);
            return msg;
        }
    }

    @RequestMapping(value = "getBorrowInfo", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getBorrowInfo(SearchFilter searchFilter) {
        ReturnMsg msg = new ReturnMsg();
        try {
            GetBorrowInfoReq reqBean = new GetBorrowInfoReq(searchFilter);
            GetBorrowInfoResp respBean = new GetBorrowInfoResp();

            int uid = (int) SessionUtil.getSessionValue("uid");
            if (uid == 0) {
                LOGGER.error("get borrow info failed");
                msg.setMsg(1, "get borrow info failed", null);
                return msg;
            }
            reqBean.setUid(uid);

            respBean = getBorrowInfoService.getBorrowInfoRecord(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("get borrow info failed");
                msg.setMsg(1, "get borrow info failed", null);
                return msg;
            }
            respBean.setPage(searchFilter.getPage());
            msg.setMsg(0, "get borrow info success", respBean);
            return msg;
        } catch (Exception e) {
            LOGGER.error("BorrowController getBorrowInfo() catch exception : ", e);
            msg.setMsg(1, "get borrow info failed", null);
            return msg;
        }
    }
}
