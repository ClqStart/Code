package com.clq.mapper;


import com.clq.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);
    //根据用户名查找指定用户对象
    public User getUserByNameIsExit(String username);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}