package com.clq.services;

import com.clq.pojo.FriendsRequest;
import com.clq.pojo.User;
import com.clq.vo.FriendsRequestVo;
import com.clq.vo.MyFriendsVo;

import java.util.List;

/**
 * @author root
 */
public interface UserServices {

    User getUserById(String id);

    //根据名字查询信息
    User getUserByNameIsExit(String username);

    User insert(User user);

    //修改用户
    User updateUserInfo(User user);

    //搜索好友的前置条件接口
    Integer preconditionSearchFriend(String myUserId, String friendUserName);

    //发送好友请求
    void sendFriendRequest(String muUserId, String friendUserName);

    /**
     * 好友请求列表
     * @param acceptUserId
     * @return
     */
    List<FriendsRequestVo> queryFriendRequestList(String acceptUserId);

    //处理好友请求——忽略好友请求
    void deleteFriendRequest(FriendsRequest friendsRequest);

    //处理好友请求——通过好友请求
    void passFriendRequest(String sendUserId,String acceptUserId);

    //好友列表查询
    List<MyFriendsVo> queryMyFriends(String userId);

}
