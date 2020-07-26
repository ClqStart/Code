package com.clq.mapper;


import com.clq.vo.FriendsRequestVo;
import com.clq.vo.MyFriendsVo;

import java.util.List;

public interface UserMapperCustom {
    /**
     * 查询朋友请求列表
     * @param acceptUserId：接收方
     * @return
     */
    List<FriendsRequestVo> queryFriendRequestList(String acceptUserId);

    /**
     * 查询好友列表
     * @param userId
     * @return
     */
    List<MyFriendsVo> queryMyFriends(String userId);
}
