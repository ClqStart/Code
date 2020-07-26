package com.kuang.mapper;

import com.kuang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

/*
 *@author:C1q
 */
@Mapper
@Repository
public interface UserMapper {

    List<User> queryUser();

    User queryUserById(@PathParam("id") int id);

    int addUser(User user);

    int UpdateUser(User user);

    int deleteUser(@PathParam("id") int id);

}
