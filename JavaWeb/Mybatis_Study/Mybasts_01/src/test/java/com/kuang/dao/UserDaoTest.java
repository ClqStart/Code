package com.kuang.dao;

import com.kuang.pojo.User;
import com.kuang.utils.MybatilsUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {

    @Test
    public void test() {
        //第一步获取sqlSession对象
        SqlSession sqlSession = MybatilsUtils.getSqlSession();

        //方式一：getMapper
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userDao.getUserList();

        for (User user : userList) {
            System.out.println(user);
        }
        //关闭sqlSession
        sqlSession.close();
    }
    @Test
    public void getUserLike() {
        SqlSession sqlSession = MybatilsUtils.getSqlSession();
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        List<User> Likes = userDao.getUserLike("李"); // 注意传递通配符非法注入
        for (User like : Likes) {
            System.out.println(like);
        }
        sqlSession.close();
    }

    @Test
    public void getUserById() {
        SqlSession sqlSession = MybatilsUtils.getSqlSession();
        UserMapper userDao = sqlSession.getMapper(UserMapper.class);
        User useById = userDao.getUseById(4);
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
    public void addUser2() {
        SqlSession sqlSession = MybatilsUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId",4);
        map.put("userName","李5");
        map.put("userPwd","23444444");
        int ma = mapper.addUser2(map);
        if(ma>0){
            System.out.println("添加成功");
        }
        sqlSession.commit();
        sqlSession.close();
        test();
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
