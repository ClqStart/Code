package com.kuang.dao;

import com.kuang.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    List<User> getUserLike(String value);
    //查询全部用户
    List<User>  getUserList();

    //根据ID查询用户
    User  getUseById(int id);

    //insert 一个用户
    int addUser(User user);
    int addUser2(Map map);

    //修改用户
    int  updateUser(User user);

    //删除一个用户
    int  deleteUser(int id);
}
