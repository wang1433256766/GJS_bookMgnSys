package cn.com.miaoto.modules.book.impl;

import cn.com.miaoto.common.SiomConstants;
import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookDao;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.dao.inf.FollowDao;
import cn.com.miaoto.modules.book.inf.GetBookInfoByBidService;
import cn.com.miaoto.modules.book.model.GetBookInfoByBidReq;
import cn.com.miaoto.modules.book.model.GetBookInfoByBidResp;
import cn.com.miaoto.pojo.Book;
import cn.com.miaoto.pojo.BookEntity;
import cn.com.miaoto.pojo.Follow;
import cn.com.miaoto.util.SessionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hx on 2017/7/21.
 */
@Service
public class GetBookInfoByBidServiceImpl extends AbstractService<GetBookInfoByBidReq, GetBookInfoByBidResp> implements GetBookInfoByBidService {

    @Resource
    BookDao bookDao;

    @Resource
    BookEntityDao bookEntityDao;

    @Resource
    FollowDao followDao;

    @Override
    public GetBookInfoByBidResp getBookInfoByBid(GetBookInfoByBidReq reqBean, GetBookInfoByBidResp respBean) {
        return (GetBookInfoByBidResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetBookInfoByBidReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetBookInfoByBidReq reqBean, GetBookInfoByBidResp respBean) throws Exception {
        Book book = bookDao.queryBookById(reqBean.getBid());
        if (book == null) {
            return;
        }
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBid(reqBean.getBid());

        Object obj = SessionUtil.getSessionValue("uid");
        if (obj != null) {
            bookEntity.setQueryUID((int) obj);
        }
        List<BookEntity> bookEntityList = bookEntityDao.queryBookEntityByBar(bookEntity);
        if (book == null) {
            return;
        }
        respBean.setBook(book);
        respBean.setBookEntityList(bookEntityList);

        // 查询关注的状态
        Object uid = SessionUtil.getSessionValue("uid");
        if (uid == null) {
            return;
        }

        // 查询可借数量
        int available = bookEntityDao.queryBookEntityCount(book.getBid(), SiomConstants.BOOKSTATUS_AVAILABLE);
        if (available == 0) {
            Follow follow = new Follow();
            follow.setFoUID((int) uid);
            follow.setFoStatus(SiomConstants.FOLLOW_STATUS_FOLLOWED);
            follow.setFoBeid(book.getBid());
            List<Follow> followList = followDao.selectFollowing(follow);
            if (followList == null || followList.size() == 0) {
                respBean.setCanFollow(SiomConstants.FOLLOW_CAN_FOLLOW);
                return;
            } else {
                respBean.setCanFollow(SiomConstants.FOLLOW_FOLLOWED);
                return;
            }
        }

        // 有可借图书, 不能关注
        respBean.setCanFollow(SiomConstants.FOLLOW_CANNOT_FOLLOW);
    }
}
