package cn.com.miaoto.controller;

import cn.com.miaoto.common.BookStats;
import cn.com.miaoto.common.ReturnMsg;
import cn.com.miaoto.common.SiomConstants;
import cn.com.miaoto.common.mvcBean.BaseController;
import cn.com.miaoto.modules.book.inf.*;
import cn.com.miaoto.modules.book.model.*;
import cn.com.miaoto.pojo.Book;
import cn.com.miaoto.pojo.BookEntity;
import cn.com.miaoto.pojo.common.AddBookReturn;
import cn.com.miaoto.pojo.common.SearchFilter;
import cn.com.miaoto.util.SessionUtil;
import cn.com.miaoto.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * book相关controller
 */
@Controller
@RequestMapping("/")
public class BookController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Resource
    private GetBookInfoService getBookInfoService;

    @Resource
    private AddBookEntityService addBookEntityService;

    @Resource
    private BookSearchService bookSearchService;

    @Resource
    private AddBookService addBookSerice;

    @Resource
    private GetUncheckBookService getUncheckBookService;

    @Resource
    private UpdateBookService updateBookService;

    @Resource
    private UpdateBookEntityService updateBookEntityService;

    @Resource
    private GetBookInfoByBidService getBookInfoByBidService;

    @Resource
    private GetAllBookEntityService getAllBookEntityService;

    @Resource
    private GetBookByBatchService getBookByBatchService;

    @Resource
    private GetHotBookService getHotBookService;

    @Resource
    private GetHotBorrowService getHotBorrowService;

    @Resource
    private GetNewBookService getNewBookService;

    @Resource
    private FuzzySearchService fuzzySearchService;

    @Resource
    private GetClaimNumberService getClaimNumberService;

    @Resource
    private BookStats bookStats;

    /**
     * 通过条码获取书目信息
     *
     * @param barCode 条码
     * @return ReturnMsg
     */
    @RequestMapping(value = "getBookInfo", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getBookInfo(String barCode) {
        ReturnMsg msg = new ReturnMsg();
        try {
            GetBookInfoReq reqBean = new GetBookInfoReq(barCode);
            GetBookInfoResp respBean = new GetBookInfoResp();
            respBean = getBookInfoService.getBookInfoBycode(reqBean, respBean);
            if (respBean != null && respBean.getBookEntity() != null) {
                msg.setMsg(0, "get bookInfo success", respBean.getBookEntity());
            } else {
                msg.setMsg(1, "get bookInfo failed", null);
            }
            return msg;
        } catch (Exception e) {
            LOGGER.error("getBookInfo() catch exception : ", e);
            msg.setMsg(1, "get bookInfo failed", null);
            return msg;
        }
    }

    /**
     * 多条件检索
     *
     * @param book   书目信息
     * @param filter 排序和分页条件
     * @return ReturnMsg
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg search(Book book, SearchFilter filter) {
        ReturnMsg msg = new ReturnMsg();
        try {
            int begin = (filter.getPage() - 1) * filter.getRows();
            if (begin < 0) {
                begin = 0;
            }
            int end = filter.getPage() * filter.getRows();
            filter.setBegin(begin);
            filter.setEnd(end);
            BookSearchReq reqBean = new BookSearchReq(filter, book);
            BookSearchResp respBean = new BookSearchResp();
            respBean = bookSearchService.bookSearch(reqBean, respBean);
            if (respBean.getBookList() != null) {
                msg.setMsg(0, "search successs", respBean);
            } else {
                msg.setMsg(1, "search failed", null);
            }
            return msg;
        } catch (Exception e) {
            LOGGER.error("search() catch exception : ", e);
            msg.setMsg(1, "search failed", null);
            return msg;
        }
    }

    /**
     * 全文检索
     *
     * @param bookName 检索内容
     * @param filter   排序和分页条件
     * @return ReturnMsg
     */
    @RequestMapping(value = "fuzzySearch", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg fuzzySearch(SearchFilter filter, String bookName) {
        ReturnMsg msg = new ReturnMsg();
        try {
            int begin = (filter.getPage() - 1) * filter.getRows();
            if (begin < 0) {
                begin = 0;
            }
            int end = filter.getPage() * filter.getRows();
            filter.setBegin(begin);
            filter.setEnd(end);
            FuzzySearchReq reqBean = new FuzzySearchReq(filter, bookName);
            FuzzySearchResp respBean = new FuzzySearchResp();
            respBean = fuzzySearchService.fuzzySearch(reqBean, respBean);
            respBean.setPage(filter.getPage());
            if (respBean.getBookList() != null) {
                msg.setMsg(0, "search successs", respBean);
            } else {
                msg.setMsg(1, "search failed", null);
            }
            return msg;
        } catch (Exception e) {
            LOGGER.error("search() catch exception : ", e);
            msg.setMsg(1, "search failed", null);
            return msg;
        }
    }

    /**
     * 编目
     *
     * @param book       书目信息
     * @param bookEntity 实体信息
     * @param numOfBook  录入的书目
     * @return ReturnMsg
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg edit(Book book, BookEntity bookEntity, int numOfBook) {
        ReturnMsg msg = new ReturnMsg();
        try {
            if (bookEntity.getBid() != null) {
                GetBookInfoByBidReq reqBean2 = new GetBookInfoByBidReq(bookEntity.getBid());
                GetBookInfoByBidResp respBean2 = new GetBookInfoByBidResp();
                respBean2 = getBookInfoByBidService.getBookInfoByBid(reqBean2, respBean2);
                // 副本
                if (numOfBook > 1) {
                    //多本
                    List<Long> longList = new ArrayList<>();
                    AddBookReturn addBookReturn = new AddBookReturn();
                    addBookReturn.setBookName(respBean2.getBook().getName());
                    boolean isSuccess = true;
                    boolean isForeign = true;
                    if (respBean2.getBook().getLang().trim().equals("chi")) {
                        isForeign = false;
                    }
                    for (int i = 0; i < numOfBook; i++) {
                        AddBookEntityReq reqBean = new AddBookEntityReq(bookEntity);
                        reqBean.setForeign(isForeign);
                        AddBookEntityResp respBean = new AddBookEntityResp();
                        respBean = addBookEntityService.addBookEntity(reqBean, respBean);
                        //设置返回的信息(条形码,书名,结果)
                        longList.add(respBean.getBarcode());
                        if (respBean.getResult() == 0) {
                            isSuccess = false;
                            LOGGER.error("insert book barcode = {} failed", respBean.getBarcode());
                        }
                    }
                    addBookReturn.setBarcodeList(longList);
                    if (!isSuccess) {
                        msg.setMsg(1, "add book failed", addBookReturn);
                        return msg;
                    }
                    msg.setMsg(0, "add book success", addBookReturn);
                    return msg;
                } else {
                    //单本
                    AddBookEntityReq reqBean = new AddBookEntityReq(bookEntity);
                    AddBookEntityResp respBean = new AddBookEntityResp();
                    respBean = addBookEntityService.addBookEntity(reqBean, respBean);
                    //设置返回的信息(条形码,书名,结果)
                    AddBookReturn addBookReturn = new AddBookReturn();
                    List<Long> longList = new ArrayList<>();
                    longList.add(respBean.getBarcode());
                    addBookReturn.setBarcodeList(longList);
                    addBookReturn.setBookName(respBean2.getBook().getName());
                    if (respBean.getResult() == 0) {
                        msg.setMsg(1, "add bookEntity failed", addBookReturn);
                        return msg;
                    }

                    msg.setMsg(0, "add bookEntity success", addBookReturn);
                    return msg;
                }
            } else {
                // 新书
                // 检查语种
                HashSet hashSet = new HashSet() {{
                    add(SiomConstants.LANG_CN);
                    add(SiomConstants.LANG_EN);
                    add(SiomConstants.LANG_DE);
                    add(SiomConstants.LANG_FR);
                    add(SiomConstants.LANG_RU);
                    add(SiomConstants.LANG_JP);
                    add(SiomConstants.LANG_Other);
                }};
                if (!hashSet.contains(book.getLang())) {
                    LOGGER.error("book lang is wrong");
                    msg.setMsg(1, "book lang is wrong", null);
                    return msg;
                }
                //设置addBookreturn
                AddBookReturn addBookReturn = new AddBookReturn();
                addBookReturn.setBookName(book.getName());

                AddBookReq reqBean = new AddBookReq(book, bookEntity);
                AddBookResp respBean = new AddBookResp();
                reqBean.setNumOfBook(numOfBook);
                respBean = addBookSerice.addBook(reqBean, respBean);

                addBookReturn.setBarcodeList(respBean.getBarcodeList());
                if (respBean.getResult() == 0) {
                    msg.setMsg(1, "add book failed", addBookReturn);
                    return null;
                }
                msg.setMsg(0, "add book success", addBookReturn);
                return msg;
            }
        } catch (Exception e) {
            LOGGER.error("edit() catch exception : ", e);
            msg.setMsg(1, "add book failed", null);
            return msg;
        }
    }

    /**
     * 获取未审目图书
     *
     * @return ReturnMsg
     */
    @Deprecated
    @RequestMapping(value = "getUncheckBook", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getUncheckBook() {
        ReturnMsg msg = new ReturnMsg();
        try {
            int batchId = 1;
            GetUncheckBookReq reqBean = new GetUncheckBookReq(batchId);
            GetUncheckBookResp respBean = new GetUncheckBookResp();
            respBean = getUncheckBookService.getUNcheckedBook(reqBean, respBean);
            if (respBean.getBookEntityList() == null) {
                msg.setMsg(1, "get unchecked book failed", null);
            }
            msg.setMsg(0, "get unchecked book success", respBean.getBookEntityList());
            return msg;
        } catch (Exception e) {
            LOGGER.error("getUncheckBook() catch exception : ", e);
            msg.setMsg(1, "get unchecked book failed", null);
            return msg;
        }
    }

    /**
     * 审目
     *
     * @param book       书目信息
     * @param bookEntity 图书实体
     * @return ReturnMsg
     */
    @RequestMapping(value = "checkBook", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg checkBook(Book book, BookEntity bookEntity) {
        ReturnMsg msg = new ReturnMsg();
        try {
            //update book
            UpdateBookReq reqBean = new UpdateBookReq(book);
            UpdateBookResp respBean = new UpdateBookResp();
            respBean = updateBookService.updateBook(reqBean, respBean);

            //update bookentity
            bookEntity.setStatus(SiomConstants.BOOKSTATUS_AVAILABLE);
            bookEntity.setSmUid((int) SessionUtil.getSessionValue("uid"));
            bookEntity.setSmTime(TimeUtil.getCurrentTimeStr());
            UpdateBookEntityReq reqBean2 = new UpdateBookEntityReq(bookEntity);
            UpdateBookEntityResp respBean2 = new UpdateBookEntityResp();
            respBean2 = updateBookEntityService.updateBook(reqBean2, respBean2);

            if (respBean2.getResult() != 0) {
                msg.setMsg(0, "check book success", null);
            } else {
                msg.setMsg(1, "check book failed", null);
            }
            return msg;
        } catch (Exception e) {
            LOGGER.error("checkBook() catch exception : ", e);
            msg.setMsg(1, "check book failed", null);
            return msg;
        }
    }

    /**
     * 获取书目信息
     *
     * @param bid 书目id
     * @return ReturnMsg
     */
    @RequestMapping(value = "getBookInfoByBid", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getBookInfoByBid(int bid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            // 触发图书浏览统计
            bookStats.addBookView(bid);

            // 处理
            GetBookInfoByBidReq reqBean = new GetBookInfoByBidReq(bid);
            GetBookInfoByBidResp respBean = new GetBookInfoByBidResp();
            respBean = getBookInfoByBidService.getBookInfoByBid(reqBean, respBean);
            if (respBean != null && respBean.getBook() != null) {
                msg.setMsg(0, "get bookInfo success", respBean);
            } else {
                msg.setMsg(1, "get bookInfo failed", null);
            }
            return msg;
        } catch (Exception e) {
            LOGGER.error("getBookInfoByBid() catch exception : ", e);
            msg.setMsg(1, "get bookInfo failed", null);
            return msg;
        }
    }

    /**
     * 获取书目实体
     *
     * @param searchFilter 排序和分页条件
     * @param book         书目信息
     * @param bookEntity   书目实体
     * @return ReturnMsg
     */
    @RequestMapping(value = "getAllBookentity", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg getAllBookentity(SearchFilter searchFilter, Book book, BookEntity bookEntity, @RequestParam(value = "bookName", required = false) String bookName) {
        ReturnMsg msg = new ReturnMsg();
        try {
            book.setName(bookName);
            GetAllBookEntityReq reqBean = new GetAllBookEntityReq(searchFilter, book, bookEntity);
            GetAllBookEntityResp respBean = new GetAllBookEntityResp();
            respBean = getAllBookEntityService.getAllBookEntity(reqBean, respBean);
            if (respBean.getBookEntityList() == null) {
                msg.setMsg(1, "get all bookentity failed", null);
                return msg;
            }
            msg.setMsg(0, "get all bookentity success", respBean);
        } catch (Exception e) {
            LOGGER.error("BookController getAllBookentity() catch exception ", e);
            msg.setMsg(1, "get all bookentity failed", null);
            return msg;
        }
        return msg;
    }

    /**
     * 更新图书(包括书目表和实体表)
     *
     * @param book       书目信息
     * @param bookEntity 图书实体
     * @return ReturnMsg
     */
    @RequestMapping(value = "updateBook", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg updateBook(Book book, BookEntity bookEntity) {
        ReturnMsg msg = new ReturnMsg();
        UpdateBookEntityResp respBean2 = new UpdateBookEntityResp();
        try {
            UpdateBookResp respBean = new UpdateBookResp();
            //update book
            if (book.getBid() != null) {
                UpdateBookReq reqBean = new UpdateBookReq(book);
                respBean = updateBookService.updateBook(reqBean, respBean);
            }

            //update bookentity
            if (bookEntity.getBeid() != null || bookEntity.getBeidStr() != null) {
                UpdateBookEntityReq reqBean2 = new UpdateBookEntityReq(bookEntity);
                respBean2 = updateBookEntityService.updateBook(reqBean2, respBean2);
            }


            if (respBean.getResultCode() == 1 || respBean2.getResult() == 1) {
                msg.setMsg(0, "update book success", respBean2.getBorrowList());
            } else {
                msg.setMsg(1, "update book failed", respBean2.getBorrowList());
            }
            return msg;
        } catch (Exception e) {
            LOGGER.error("BookController updateBook() catch exception ", e);
            msg.setMsg(1, "update book failed", respBean2.getBorrowList());
            return msg;
        }
    }

    /**
     * 获取某一批次的图书
     *
     * @param batchId 批次id
     * @return ReturnMsg
     */
    @RequestMapping(value = "getBookByBatch", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getBookByBatch(int batchId) {
        ReturnMsg msg = new ReturnMsg();
        try {
            GetBookByBatchReq reqBean = new GetBookByBatchReq(batchId);
            GetBookByBatchResp respBean = new GetBookByBatchResp();
            respBean = getBookByBatchService.getBookByBatch(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("get book by batch failed");
                msg.setMsg(1, "get book by batch failed", null);
                return msg;
            }
            msg.setMsg(0, "get book by batch success", respBean.getBookEntityList());
            return msg;
        } catch (Exception e) {
            LOGGER.error("BookController getBookByBatch() catch exception ", e);
            msg.setMsg(1, "get book by batch failed", null);
            return msg;
        }
    }

    /**
     * 获取热门图书
     *
     * @return ReturnMsg
     */
    @RequestMapping(value = "getHotBook", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getHotBook() {
        ReturnMsg msg = new ReturnMsg();
        try {
            List<Book> bookList = bookStats.getHotBooks();
            // 先读内存
            if (bookList != null && bookList.size() != 0) {
                LOGGER.trace("get hot book cache success");
                msg.setMsg(1, "get hot book success", bookList);
                return msg;
            }
            // 查表
            GetHotBookReq reqBean = new GetHotBookReq();
            GetHotBookResp respBean = new GetHotBookResp();
            respBean = getHotBookService.getHotBook(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("get hot book failed");
                msg.setMsg(1, "get hot book failed", null);
                return msg;
            }
            // 写进内存
            bookStats.setHotBooks(respBean.getBookList());
            msg.setMsg(0, "get hot book success ", respBean.getBookList());
            return msg;
        } catch (Exception e) {
            LOGGER.error("BookController getHotBook() catch exception ", e);
            msg.setMsg(1, "get hot book failed", null);
            return msg;
        }
    }

    /**
     * 获取热门借阅
     *
     * @return ReturnMsg
     */
    @RequestMapping(value = "getHotBorrow", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getHotBorrow() {
        ReturnMsg msg = new ReturnMsg();
        try {
            // 先读内存
            List<Book> bookList = bookStats.getHotBorrow();
            if (bookList != null && bookList.size() != 0) {
                LOGGER.trace("get hot borrow cache success");
                msg.setMsg(1, "get hot borrow success", bookList);
                return msg;
            }
            // 查表
            GetHotBorrowReq reqBean = new GetHotBorrowReq();
            GetHotBorrowResp respBean = new GetHotBorrowResp();
            respBean = getHotBorrowService.getHotBorrow(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("get hot borrow failed");
                msg.setMsg(1, "get hot borrow failed", null);
                return msg;
            }
            // 写进内存
            bookStats.setHotBorrow(respBean.getBookList());
            msg.setMsg(0, "get hot borrow success", respBean.getBookList());
            return msg;
        } catch (Exception e) {
            LOGGER.error("BookController getHotBorrow() catch exception ", e);
            msg.setMsg(1, "get hot borrow failed", null);
            return msg;
        }
    }

    /**
     * 获取新书推荐
     *
     * @return ReturnMsg
     */
    @RequestMapping(value = "getNewBook", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getNewBook() {
        ReturnMsg msg = new ReturnMsg();
        try {
            // 先读内存
            List<BookEntity> bookList = bookStats.getNewBooks();
            if (bookList != null && bookList.size() != 0) {
                LOGGER.trace("get new book cache success");
                msg.setMsg(1, "get new book success", bookList);
                return msg;
            }
            // 查表
            GetNewBookReq reqBean = new GetNewBookReq();
            GetNewBookResp respBean = new GetNewBookResp();
            respBean = getNewBookService.getNewBook(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("get new book failed");
                msg.setMsg(1, "get new book failed", null);
                return msg;
            }
            // 写进内存
            bookStats.setNewBooks(respBean.getBookEntityList());
            msg.setMsg(0, "get new book success", respBean.getBookEntityList());
            return msg;
        } catch (Exception e) {
            LOGGER.error("BookController getNewBook() catch exception ", e);
            msg.setMsg(1, "get new book failed", null);
            return msg;
        }
    }

    /**
     * 获取索书号
     *
     * @return
     */
    @RequestMapping(value = "getClaimNumber", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getClaimNumber(String typeNum) {
        ReturnMsg msg = new ReturnMsg();
        try {
            GetClaimNumberReq reqBean = new GetClaimNumberReq();
            GetClaimNumberResp respBean = new GetClaimNumberResp();
            reqBean.setTypeNum(typeNum);
            respBean = getClaimNumberService.getClaimNumber(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("get ClaimNumber failed");
                msg.setMsg(1, "get ClaimNumber failed", null);
                return msg;
            }
            msg.setMsg(0, "get ClaimNumber success", respBean.getClainNumber());
            return msg;
        } catch (Exception e) {
            LOGGER.error("BookController getClaimNumber() catch exception ", e);
            msg.setMsg(1, "get ClaimNumber failed", null);
            return msg;
        }
    }
}
