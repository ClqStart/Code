package com.kuang.dao;

import com.kuang.pojo.User;
import com.kuang.utils.MybatilsUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class UserMapperTest {

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
    public void getUserById() {
        //第一步获取sqlSession对象
        SqlSession sqlSession = MybatilsUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User userById = mapper.getUserById(1);
        System.out.println(userById);
        //关闭sqlSession
        sqlSession.close();
    }


    @Test
    public void addUser() {
        //第一步获取sqlSession对象
        SqlSession sqlSession = MybatilsUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

       mapper.addUser(new User(5, "hello", "1234445"));

        sqlSession.close();
    }
    @Test
    public void updateUser() {
        //第一步获取sqlSession对象
        SqlSession sqlSession = MybatilsUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.updateUser(new User(5, "大叔", "38383838"));

        sqlSession.close();
    }
    @Test
    public void deleteUser() {
        //第一步获取sqlSession对象
        SqlSession sqlSession = MybatilsUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.deleteUser(5);

        sqlSession.close();
    }

}
