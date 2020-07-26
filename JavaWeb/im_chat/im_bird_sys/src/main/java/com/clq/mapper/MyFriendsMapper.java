package com.clq.mapper;


import com.clq.pojo.MyFriends;
import org.springframework.transaction.annotation.Transactional;

public interface MyFriendsMapper {
    int deleteByPrimaryKey(String id);

    @Transactional
    int insert(MyFriends record);

    int insertSelective(MyFriends record);

    MyFriends selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MyFriends record);

    int updateByPrimaryKey(MyFriends record);

    MyFriends selectOneByExample(MyFriends mfe);
}