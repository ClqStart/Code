package com.kuang.dao;

import com.kuang.pojo.User;
import com.kuang.utils.MybatilsUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class UserMapperTest {

    static Logger  logger = Logger.getLogger(UserMapperTest.class);
    @Test
    public  void  getUserByRowBound(){
        SqlSession sqlSession = MybatilsUtils.getSqlSession();

        RowBounds rowBounds = new RowBounds(1, 2);
        List<User> userList = sqlSession.selectList("com.kuang.dao.UserMapper.getUserByRowBound",null,rowBounds);
        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();
    }

    @Test
    public void getUserByLimit() {
        //第一步获取sqlSession对象
        SqlSession sqlSession = MybatilsUtils.getSqlSession();

        //方式一：getMapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("start",2);
        map.put("pagesize",2);
        List<User> userList = userMapper.getUserByLimit(map);

        for (User user : userList) {
            System.out.println(user);
        }
        //关闭sqlSession
        sqlSession.close();
    }
    @Test
    public void getUserList() {
        //第一步获取sqlSession对象
        SqlSession sqlSession = MybatilsUtils.getSqlSession();

        //方式一：getMapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.getUserList();

        for (User user : userList) {
            System.out.println(user);
        }
        //关闭sqlSession
        sqlSession.close();
    }

    @Test
    public  void  testLog4j(){
        logger.info("info：进入selectUser方法");
        logger.debug("debug：进入selectUser方法");
        logger.error("error: 进入selectUser方法");
    }

}
