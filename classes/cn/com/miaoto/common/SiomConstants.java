package cn.com.miaoto.common;

public class SiomConstants {

    //----------------setting----------------
    /**
     * 最多续约次数
     */
    public static final String SETTING_MAXRENEWAL = "maxRenewal";
    /**
     * 借阅时间
     */
    public static final String SETTING_BORROWTIME = "borrowTime";
    /**
     * 最大借阅数量
     */
    public static final String SETTING_MAXBORROWED = "maxBorrowed";
    /**
     * 续约时长(天)
     */
    public static final String SETTING_RENEWPERIOD = "renewPeriod";

    /**
     * 外文条形码开始编号
     */
    public static final String SETTING_FOREIGN_START = "foreignStart";

    /**
     * 图书归还提醒邮件标题
     */
    public static final String SETTING_REMINDER_TITLE = "reminderTitle";

    /**
     * 图书归还提醒邮件模板
     */
    public static final String SETTING_REMINDER_TEMPLATE = "reminderTemplate";

    /**
     * 图书归还站内信标题
     */
    public static final String SETTING_REMINDER_MSG_TITLE = "reminderMsgTitle";

    /**
     * 图书归还站内信模板
     */
    public static final String SETTING_REMINDER_MSG_TEMPLATE = "reminderMsgTemplate";

    /**
     * 预约过期时间
     */
    public static final String SETTING_RESERVE_FAIL = "reserveFail";

    /**
     * 预约超期邮件标题
     */
    public static final String SETTING_RESERVE_FAIL_TITLE = "reserveFailTitle";

    /**
     * 预约超期邮件模板
     */
    public static final String SETTING_RESERVE_FAIL_TEMPLATE = "reserveFailTemplate";

    /**
     * 预约超期站内信标题
     */
    public static final String SETTING_RESERVE_FAIL_MSG_TITLE = "reserveFailMsgTitle";

    /**
     * 预约超期站内信模板
     */
    public static final String SETTING_RESERVE_FAIL_MSG_TEMPLATE = "reserveFailMsgTemplate";

    /**
     * 关注提醒邮件标题
     */
    public static final String SETTING_FOLLOW_TITLE = "followTitle";

    /**
     * 关注提醒邮件模板
     */
    public static final String SETTING_FOLLOW_TEMPLATE = "followTemplate";

    /**
     * 关注提醒站内信标题
     */
    public static final String SETTING_FOLLOW_MSG_TITLE = "followMsgTitle";

    /**
     * 关注提醒站内信模板
     */
    public static final String SETTING_FOLLOW_MSG_TEMPLATE = "followMsgTemplate";

    //----------------book status----------------
    /**
     * 可借
     */
    public static final int BOOKSTATUS_AVAILABLE = 0;
    /**
     * 已借
     */
    public static final int BOOKSTATUS_BORROWED = 1;
    /**
     * 损坏
     */
    public static final int BOOKSTATUS_BROKEN = 2;
    /**
     * 已预约
     */
    public static final int BOOKSTATUS_UNAVAILABLE = 3;
    /**
     * 未审核
     */
    public static final int BOOKSTATUS_UNCHECKED = 4;
    /**
     * 遗失
     */
    public static final int BOOKSTATUS_MISS = 5;
    /**
     * 剔旧(软删除)
     */
    public static final int BOOKSTATUS_DEL = 6;

    /**
     * 装订补修
     */
    public static final int BOOKSTATUS_REPAIR = 7;

    /**
     * 疑似遗失
     */
    public static final int BOOKSTATUS_MAY_LOST = 8;

    //----------------user type----------------
    /**
     * 普通用户
     */
    public static final int USER_TYPE_NORMAL = 0;
    /**
     * 管理员
     */
    public static final int USER_TYPE_ADMIN = 1;

    //----------------purchase status----------------
    /**
     * 待处理
     */
    public static final int PURCHASE_WAIT = 0;
    /**
     * 已订购
     */

    public static final int PURCHASE_RESOLVED = 1;
    /**
     * 已处理
     */
    public static final int PURCHASE_FINISHED = 2;

    //----------------语种------------------
    /**
     * 中文
     */
    public static final String LANG_CN = "chi";

    /**
     * 英文
     */
    public static final String LANG_EN = "eng";

    /**
     * 法文
     */
    public static final String LANG_FR = "fre";

    /**
     * 德文
     */
    public static final String LANG_DE = "dem";

    /**
     * 俄文
     */
    public static final String LANG_RU = "rus";

    /**
     * 日文
     */
    public static final String LANG_JP = "jpn" +
            "";

    /**
     * 其他
     */
    public static final String LANG_Other = "other";

    //----------------job------------------
    /**
     * 催还图书job
     */
    public static final String JOB_REMINDERBOOK = "reminderBook";

    /**
     * 催还图书job trigger
     */
    public static final String JOB_REMINDERBOOK_TRIGGER = "reminderBookTrigger";

    /**
     * 预约过期job
     */
    public static final String JOB_RESERVE_FAIL = "reserveFailJob";

    /**
     * 预约过期job trigger
     */
    public static final String JOB_RESERVE_FAIL_TRIGGER = "reserveFailJobTrigger";

    /**
     * 统计图书浏览job
     */
    public static final String JOB_BOOK_VIEW = "bookView";

    /**
     * 统计图书浏览job trigger
     */
    public static final String JOB_BOOK_VIEW_TRIGGER = "bookViewTrigger";

    /**
     * 统计图书借阅
     */
    public static final String JOB_BOOK_BORROW = "bookBorrow";

    /**
     * 统计图书借阅 trigger
     */
    public static final String JOB_BOOK_BORROW_TRIGGER = "bookBorrowTrigger";

    /**
     * indexApiCache
     */
    public static final String JOB_INDEXAPICACHE = "indexApiCache";

    /**
     * indexApiCache trigger
     */
    public static final String JOB_INDEXAPICACHE_TRIGGER = "indexApiCacheTrigger";
    /**
     * synUserInfo
     */
    public static final String JOB_SYNUSERINFO = "synUserInfo";

    /**
     * synUserInfo trigger
     */
    public static final String JOB_SYNUSERINFO_TRIGGER = "synUserInfoTrigger";

    //---------------- 权限 ------------------
    /**
     * 系统参数
     */
    public static final String PERMISSION_SETTING = "setting";

    /**
     * 借还操作
     */
    public static final String PERMISSION_BORROW_BACK = "borrowAndBack";

    /**
     * 丢失处理
     */
    public static final String PERMISSION_LOST = "lost";

    /**
     * 损坏处理
     */
    public static final String PERMISSION_BROKEN = "broken";

    /**
     * 录入操作
     */
    public static final String PERMISSION_INSERT = "insert";

    /**
     * 审目操作
     */
    public static final String PERMISSION_CHECK = "check";

    /**
     * 统计功能
     */
    public static final String PERMISSION_STATS = "stats";

    /**
     * 读者信息修改
     */
    public static final String PERMISSION_USERINFO = "userInfo";

    /**
     * 图书信息修改
     */
    public static final String PERMISSION_BOOKINFO = "bookInfo";

    /**
     * 建议反馈
     */
    public static final String PERMISSION_ADVICE = "advice";

    /**
     * 荐书反馈
     */
    public static final String PERMISSION_PURCHASE = "purchase";

    /**
     * 通知
     */
    public static final String PERMISSION_NOTI = "noti";

    //----------------follow 状态------------------
    /**
     * 已follow, 未通知
     */
    public static final int FOLLOW_STATUS_FOLLOWED = 0;

    /**
     * follow 已通知
     */
    public static final int FOLLOW_STATUS_REMINDER = 1;

    //------------------用户状态-------------------
    /**
     * 可用
     */
    public static final int USER_STATUS_OK = 0;

    /**
     * 解除合同离所
     */
    public static final int USER_STATUS_LEAVE = 1;

    /**
     * 退休
     */
    public static final int USER_STATUS_RETIRE = 2;

    /**
     * 毕业离所
     */
    public static final int USER_STATUS_GRADUATE = 3;

    /**
     * 违规停用
     */
    public static final int USER_STATUS_VIOLATION = 4;

    /**
     * 其他原因停用
     */
    public static final int USER_STATUS_OTHER = 4;

    //--------------------follow 状态-------------------
    /**
     * 有可借书,不显示关注按钮
     */
    public static final int FOLLOW_CANNOT_FOLLOW = 0;

    /**
     * 已关注
     */
    public static final int FOLLOW_FOLLOWED = 1;

    /**
     * 未关注
     */
    public static final int FOLLOW_CAN_FOLLOW = 2;


    //------------------借书错误码----------------
    /**
     * 成功
     */
    public static final int BORROW_SUCCESS = 0;

    /**
     * 书目找不到
     */
    public static final int BORROW_CANTFIND_BOOK = 1;

    /**
     * 用户不存在
     */
    public static final int BORROW_USER_NOT_EXIST = 2;

    /**
     * 用户状态有问题
     */
    public static final int BORROW_USER_STATUS = 3;

    /**
     * 其他错误
     */
    public static final int BORROW_OTHER_ERR = 4;

    /**
     * 书目实体状态有误
     */
    public static final int BORROW_BOOK_ERROR = 5;

    /**
     * 达到最大借阅数量
     */
    public static final int BORROW_MAX_NUM = 6;

    /**
     * 借阅失败
     */
    public static final int BORROW_FAILED = 7;


    //login exception
    public static int LOGIN_SUCCESS = 0;
    public static String LOGIN_SUCCESS_MSG = "Login success.";

    public static int WRONG_PASSWORD = 1;
    public static String WRONG_PASSWORD_MSG = "Wrong password.";

    public static int FAILED_TOO_MUCH = 2;
    public static String FAILED_TOO_MUCH_MSG = "Failed too much.";

    public static int USER_IS_LOCKED = 3;
    public static String USER_IS_LOCKED_MSG = "User is locked.";

    public static int USER_IS_BANNED = 4;
    public static String USER_IS_BANNED_MSG = "User is banned.";

    public static int USER_IS_TIMEOUT = 5;
    public static String USER_IS_TIMEOUT_MSG = "User is timeout.";

    public static int USER_IS_NOT_EXIST = 6;
    public static String USER_IS_NOT_EXIST_MSG = "User is not exist.";

    public static int USER_IS_NOT_AUTH = 7;
    public static String USER_IS_NOT_AUTH_MSG = "User is not auth.";

    public static int LOGIN_FAILED = 8;
    public static String LOGIN_FAILED_MSG = "Login failed.";

    public static int LOGIN_WAITTING_PERMIT = 9;
    public static String LOGIN_WAITTING_PERMIT_MSG = "waitting admin permit";

    public static int LOGIN_ADMIN_NOT_PERMIT = 10;
    public static String LOGIN_ADMIN_NOT_PERMIT_MSG = "not permitted";


}
