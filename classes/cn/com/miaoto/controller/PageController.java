package cn.com.miaoto.controller;

import cn.com.miaoto.common.mvcBean.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController extends BaseController {
    public static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    //redirect
    @RequestMapping("/index")
    public String indexPage() {
        return "redirect:/bookQueryCus/bookQueryCusSimple";
    }

    @RequestMapping("/")
    public String defaultPage() {
        return "redirect:/bookQueryCus/bookQueryCusSimple";
    }

    @RequestMapping("/bookQuery")
    public String bookQueryPage() {
        return "redirect:/bookQuery/bookQuerySimple";
    }

    //page
    @RequestMapping("/recommendation")
    public String recommendationPage() {
        return "recommendation";
    }

    @RequestMapping("/suggestion")
    public String suggestionPage() {
        return "suggestion";
    }

    @RequestMapping("/bookclass")
    public String bookclassPage() {
        return "bookclass";
    }

    @RequestMapping("/bookdetail")
    public String bookdetailPage() {
        return "bookdetail";
    }

    @RequestMapping("myBooksInfo/messagesDetail")
    public String messagesDetailPage() {
        return "myBooksInfo/messagesDetail";
    }

    @RequestMapping("suggestion-customer-history")
    public String suggestionCustomerHistoryPage() {
        return "suggestion-customer-history";
    }

    @RequestMapping("suggestionAnswer")
    public String suggestionAnswerPage() {
        return "suggestionAnswer";
    }


    @RequestMapping("/booktemplist")
    public String booktemplistPage() {
        return "booktemplist";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    //bookBorrowed
    @RequestMapping("/bookBorrowed/bookBorrow")
    public String bookBorrowPage() {
        return "bookBorrowed/bookBorrow";
    }

    @RequestMapping("/bookBorrowed/bookReturn")
    public String bookReturnPage() {
        return "bookBorrowed/bookReturn";
    }

    //bookMgn
    @RequestMapping("/bookMgn/bookAdd")
    public String bookAddPage() {
        return "bookMgn/bookAdd";
    }

    //bookMgn
    @RequestMapping("/bookMgn/bookListMgn")
    public String bookListMgnPage() {
        return "bookMgn/bookListMgn";
    }

    @RequestMapping("/bookMgn/bookAuditList")
    public String bookAuditListPage() {
        return "bookMgn/bookAuditList";
    }

    @RequestMapping("/bookMgn/bookList")
    public String bookListPage() {
        return "bookMgn/bookList";
    }

    @RequestMapping("/bookMgn/bookLose")
    public String bookLosePage() {
        return "bookMgn/bookLose";
    }

    @RequestMapping("/bookMgn/bookLoseList")
    public String bookLoseListPage() {
        return "bookMgn/bookLoseList";
    }

    @RequestMapping("/bookMgn/bookAuditDetail")
    public String bookAuditDetailPage() {
        return "bookMgn/bookAuditDetail";
    }

    @RequestMapping("/bookMgn/bookStats")
    public String bookStatsPage() {
        return "bookMgn/bookStats";
    }

    //bookQuery
    @RequestMapping("/bookQuery/bookQueryComplex")
    public String bookQueryComplexPage() {
        return "bookQuery/bookQueryComplex";
    }

    @RequestMapping("/bookQuery/bookQueryListDetail")
    public String bookQueryListDetailPage() {
        return "bookQuery/bookQueryListDetail";
    }

    @RequestMapping("/bookQuery/bookQueryList")
    public String bookQueryListPage() {
        return "bookQuery/bookQueryList";
    }

    @RequestMapping("/bookQuery/bookQuerySimple")
    public String bookQuerySimplePage() {
        return "bookQuery/bookQuerySimple";
    }

    //userMgn
    @RequestMapping("/userMgn/roleMgn")
    public String roleMgnPage() {
        return "userMgn/roleMgn";
    }


    @RequestMapping("/userMgn/registerAudit")
    public String registerAuditPage() {
        return "userMgn/registerAudit";
    }

    @RequestMapping("/userMgn/userMgn")
    public String userMgnPage() {
        return "userMgn/userMgn";
    }

    //sysConfig
    @RequestMapping("/sysConfig/sysNotification")
    public String sysNotificationPage() {
        return "sysConfig/sysNotification";
    }

    @RequestMapping("/sysConfig/sysNotificationList")
    public String sysNotificationListPage() {
        return "sysConfig/sysNotificationList";
    }

    @RequestMapping("/sysConfig/sysSetting")
    public String sysSettingPage() {
        return "sysConfig/sysSetting";
    }

    // bookQueryCus
    @RequestMapping("/bookQueryCus/bookQueryCusListDetail")
    public String bookQueryCusListDetailPage() {
        return "bookQueryCus/bookQueryCusListDetail";
    }

    @RequestMapping("/bookQueryCus/bookQueryCusList")
    public String bookQueryCusListPage() {
        return "bookQueryCus/bookQueryCusList";
    }

    @RequestMapping("/bookQueryCus/bookQueryCusComplex")
    public String bookQueryCusComplexPage() {
        return "bookQueryCus/bookQueryCusComplex";
    }

    @RequestMapping("/bookQueryCus/bookQueryCusSimple")
    public String bookQueryCusSimplePage() {
        return "bookQueryCus/bookQueryCusSimple";
    }

    //recommendationCus
    @RequestMapping("/recommendationCus/recomHistory")
    public String recomHistoryPage() {
        return "recommendationCus/recomHistory";
    }

    @RequestMapping("/recommendationCus/recomCus")
    public String recomCusPage() {
        return "recommendationCus/recomCus";
    }

    //myBooksInfo
    @RequestMapping("/myBooksInfo/messages")
    public String messagesPage() {
        return "myBooksInfo/messages";
    }

    @RequestMapping("/myBooksInfo/borrowHistory")
    public String borrowHistoryPage() {
        return "myBooksInfo/borrowHistory";
    }

    @RequestMapping("/myBooksInfo/myInfo")
    public String myInfoPage() {
        return "myBooksInfo/myInfo";
    }

    @RequestMapping("/suggestion-customer")
    public String suggestionCustomerPage() {
        return "suggestion-customer";
    }
}
