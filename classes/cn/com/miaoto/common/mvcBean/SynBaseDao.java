package cn.com.miaoto.common.mvcBean;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hx on 2017/9/11.
 */
public class SynBaseDao extends SqlSessionDaoSupport {

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplateEx) {
        super.setSqlSessionTemplate(sqlSessionTemplateEx);
    }
}
