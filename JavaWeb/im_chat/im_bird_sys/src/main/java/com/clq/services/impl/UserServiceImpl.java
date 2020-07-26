package com.clq.services.impl;

/*
 *@author:C1q
 */

import com.clq.enums.SearchFriendsStatusEnum;
import com.clq.mapper.FriendsRequestMapper;
import com.clq.mapper.MyFriendsMapper;
import com.clq.mapper.UserMapper;
import com.clq.mapper.UserMapperCustom;
import com.clq.pojo.FriendsRequest;
import com.clq.pojo.MyFriends;
import com.clq.pojo.User;
import com.clq.services.UserServices;
import com.clq.utils.FastDFSClient;
import com.clq.utils.FileUtils;
import com.clq.utils.QRCodeUtils;
import com.clq.vo.FriendsRequestVo;
import com.clq.vo.MyFriendsVo;
import com.idworker.Sid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MyFriendsMapper myFriendsMapper;

    @Autowired
    FriendsRequestMapper friendsRequestMapper;

    @Autowired
    UserMapperCustom userMapperCustom;

    @Autowired
    Sid sid;

    @Autowired
    QRCodeUtils qrCodeUtils;

    @Autowired
    FastDFSClient fastDFSClient;


    @Override
    public User getUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUserByNameIsExit(String username) {
        return userMapper.getUserByNameIsExit(username);
    }

    @Override
    public User insert(User user) {
        String userId = sid.nextShort();
        //为每个注册用户生成一个唯一的二维码
        String qrCodePath = "D://user" + userId + "qrcode.png";
        //创建二维码对象信息
        qrCodeUtils.createQRCode(qrCodePath, "bird_qrcode:" + user.getUsername());
        MultipartFile qrcodeFile = FileUtils.fileToMultipart(qrCodePath);
        String qrCodeURL = "";
        try {
            assert qrcodeFile != null;
            qrCodeURL = fastDFSClient.uploadQRCode(qrcodeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setId(userId);
        user.setQrcode(qrCodeURL);
        user.setFaceImageBig("rBHsh162lqqAaYdYAABsK9p8NiQ036.png");
        user.setFaceImage("rBHsh162lqqAaYdYAABsK9p8NiQ036_150x150.png");
        userMapper.insert(user);
        return user;
    }

    @Override
    public User updateUserInfo(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return userMapper.selectByPrimaryKey(user.getId());
    }

    @Override
    public Integer preconditionSearchFriend(String myUserId, String friendUserName) {
        User user = getUserByNameIsExit(friendUserName);
        //1.搜索的用户如果不存在，则返回【无此用户】
        if (user == null) {
            return SearchFriendsStatusEnum.USER_NOT_EXIST.status;
        }
        //2.搜索的账号如果是你自己，则返回【不能添加自己】
        if (myUserId.equals(user.getId())) {
            return SearchFriendsStatusEnum.NOT_YOURSELF.status;
        }
        //3.搜索的朋友已经是你好友，返回【该用户已经是你的好友】
        MyFriends myfriend = new MyFriends();
        myfriend.setMyUserId(myUserId);
        myfriend.setMyFriendUserId(user.getId());
        MyFriends myF = myFriendsMapper.selectOneByExample(myfriend);
        if (myF != null) {
            return SearchFriendsStatusEnum.ALREADY_FRIENDS.status;
        }
        return SearchFriendsStatusEnum.SUCCESS.status;
    }

    @Override
    public void sendFriendRequest(String myUserId, String friendUserName) {
        User user = getUserByNameIsExit(friendUserName);
        MyFriends myfriend = new MyFriends();
        myfriend.setMyUserId(myUserId);
        myfriend.setMyFriendUserId(user.getId());
        MyFriends myF = myFriendsMapper.selectOneByExample(myfriend);
        if (myF == null) {
            FriendsRequest friendsRequest = new FriendsRequest();
            String requestId = sid.nextShort();
            friendsRequest.setId(requestId);
            friendsRequest.setSendUserId(myUserId);
            friendsRequest.setAcceptUserId(user.getId());
            friendsRequest.setRequestDateTime(new Date());
            friendsRequestMapper.insert(friendsRequest);
        }
    }

    @Override
    public List<FriendsRequestVo> queryFriendRequestList(String acceptUserId) {
        return userMapperCustom.queryFriendRequestList(acceptUserId);
    }

    @Override
    public void deleteFriendRequest(FriendsRequest friendsRequest) {
        friendsRequestMapper.deleteByFriendRequest(friendsRequest);
    }

    @Override
    public void passFriendRequest(String sendUserId, String acceptUserId) {

        //双向绑定
        savaFriends(sendUserId, acceptUserId);
        savaFriends(acceptUserId, sendUserId);

        //好友请求删除
        FriendsRequest friendsRequest = new FriendsRequest();
        friendsRequest.setSendUserId(sendUserId);
        friendsRequest.setAcceptUserId(acceptUserId);
        deleteFriendRequest(friendsRequest);
    }

    @Override
    public List<MyFriendsVo> queryMyFriends(String userId) {
        return userMapperCustom.queryMyFriends(userId);
    }
    //通过好友请求保存保存到好友my_friend数据库中

    public void savaFriends(String sendUserId, String acceptUserId) {
        MyFriends myFriend = new MyFriends();
        String record = sid.nextShort();

        myFriend.setId(record);
        myFriend.setMyUserId(sendUserId);
        myFriend.setMyFriendUserId(acceptUserId);
        myFriendsMapper.insert(myFriend);
    }
}