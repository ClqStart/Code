package com.kuang.dao;

import com.kuang.pojo.User;
import com.kuang.utils.MybatilsUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapperTest {

    @Test
    public void test() {
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
        SqlSession sqlSession = MybatilsUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User useById = userMapper.getUseById(4);
        System.out.println(useById);
        sqlSession.close();
    }

    @Test
    public void addUser() {
        SqlSession sqlSession = MybatilsUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int ma = mapper.addUser(new User(4, "哈哈", "15"));
        if(ma>0){
            System.out.println("添加成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }
   
    @Test
    public  void  updateUser(){
        SqlSession sqlSession = MybatilsUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int up = mapper.updateUser(new User(4, "hehe", "11"));
        if(up>0){
            System.out.println("更新成功");
        }
        sqlSession.commit();
        sqlSession.close();

    }
    @Test
    public  void  deleteUser(){
        SqlSession sqlSession = MybatilsUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.deleteUser(4);
        if(i>0){
            System.out.println("删除成功");
        }
        sqlSession.commit();
        sqlSession.close();
        test();
    }



}
