package com.clq.controller;


import com.clq.bo.UserBo;
import com.clq.enums.OperatorFriendRequestTypeEnum;
import com.clq.enums.SearchFriendsStatusEnum;
import com.clq.pojo.FriendsRequest;
import com.clq.pojo.User;
import com.clq.services.UserServices;
import com.clq.utils.*;
import com.clq.vo.FriendsRequestVo;
import com.clq.vo.MyFriendsVo;
import com.clq.vo.UserVo;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices userServices;

    //用户登录与注册一体化
    @Autowired
    FastDFSClient fastDFSClient;


    @RequestMapping("/registerOrLogin")
    @ResponseBody
    public IWdzlJSONResult registerOrlogin(User user) {

        User userResult = userServices.getUserByNameIsExit(user.getUsername());
        if (userResult != null) {

            if (!userResult.getPassword().equals(MD5Utils.getPwd(user.getPassword()))) {
                return IWdzlJSONResult.errorMsg("密码不正确");
            }
        } else {
            user.setNickname("CLQ");
            user.setQrcode("");
            user.setPassword(MD5Utils.getPwd(user.getPassword()));
            user.setFaceImage("");
            user.setFaceImageBig("");

            userResult = userServices.insert(user);
        }

        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userResult, userVo);
        return IWdzlJSONResult.ok(userVo);
    }


    /**
     *  好友请求列表查询
     * @param
     * @return
     */
    @RequestMapping("/operFriendRequest")
    @ResponseBody
    public IWdzlJSONResult operFriendRequest(String acceptUserId,String sendUserId,Integer operType){

        FriendsRequest friendsRequest = new FriendsRequest();
        friendsRequest.setAcceptUserId(acceptUserId);
        friendsRequest.setSendUserId(sendUserId);
        if(operType==OperatorFriendRequestTypeEnum.IGNORE.type){
            //忽略d对好友请求列表进行删除
            userServices.deleteFriendRequest(friendsRequest);

        }else if(operType==OperatorFriendRequestTypeEnum.PASS.type){
            //通过对好友列表进行添加内容
            userServices.passFriendRequest(sendUserId,acceptUserId);

        }
        //查询好友列表
        List<MyFriendsVo> myFriends = userServices.queryMyFriends(acceptUserId);
        return  IWdzlJSONResult.ok(myFriends);

    }

    @RequestMapping("/queryFriendRequest")
    @ResponseBody
    public IWdzlJSONResult queryFriendRequest(String userId){
        List<FriendsRequestVo> friendRequestList = userServices.queryFriendRequestList(userId);
        System.out.println(friendRequestList);
        return IWdzlJSONResult.ok(friendRequestList);
    }



    @RequestMapping("/uploadFaceBase64")
    @ResponseBody
    //用户头像上传访问方法
    public IWdzlJSONResult uploadFaceBase64(@RequestBody UserBo userBO) throws Exception {
        //获取前端传过来的base64的字符串，然后转为文件对象在进行上传
        String base64Data = userBO.getFaceData();
        String userFacePath = "D:\\"+userBO.getUserId()+"userFaceBase64.png";
        //调用FileUtils 类中的方法将base64 字符串转为文件对象
        FileUtils.base64ToFile(userFacePath, base64Data);
        MultipartFile multipartFile = FileUtils.fileToMultipart(userFacePath);
        //获取fastDFS上传图片的路径
        assert multipartFile != null;
        String url = fastDFSClient.uploadBase64(multipartFile);
        System.out.println(url);
        String thump = "_150x150.";
        String[] arr = url.split("\\.");
        String thumpImgUrl = arr[0]+thump+arr[1];
//        String bigFace = "dssdklsdjsdj3498458.png";
//        String thumpFace = "dssdklsdjsdj3498458_150x150.png";
        //更新用户头像
        User user = new User();
        user.setId(userBO.getUserId());
        user.setFaceImage(thumpImgUrl);
        user.setFaceImageBig(url);
        User result = userServices.updateUserInfo(user);
        return  IWdzlJSONResult.ok(result);
    }
    /**
     * 修改昵称
     * @param
     * @return
     */
    @RequestMapping("/setNickname")
    @ResponseBody
    public  IWdzlJSONResult setNickName(User user){
      User  userResult = userServices.updateUserInfo(user);
      return  IWdzlJSONResult.ok(userResult);
    }

    /**
     * 添加好友进行搜索
     * @param myUserId
     * @param friendUserName
     * @return
     */
    @RequestMapping("/searchFriend")
    @ResponseBody
    public  IWdzlJSONResult searchFriend(String myUserId,String friendUserName){

        Integer status = userServices.preconditionSearchFriend(myUserId, friendUserName);
        if(status == SearchFriendsStatusEnum.SUCCESS.status){
            User user = userServices.getUserByNameIsExit(friendUserName);
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user,userVo);
            return IWdzlJSONResult.ok(userVo);
        }else {
            String  msg = SearchFriendsStatusEnum.getMsgByKey(status);
            return IWdzlJSONResult.errorMsg(msg);
        }
    }

    /**
     * 好友列表查询
     * @param userId
     * @return
     */
    @RequestMapping("/myFriends")
    @ResponseBody
    public  IWdzlJSONResult myFriends(String userId){
            if(StringUtils.isBlank(userId)){
                return IWdzlJSONResult.errorMsg("用户id为空");
            }
        List<MyFriendsVo> myFriendsVos = userServices.queryMyFriends(userId);
        System.out.println(myFriendsVos);
        return  IWdzlJSONResult.ok(myFriendsVos);

    }



    /**
     * 发送好友添加
     * @param myUserId
     * @param friendUserName
     * @return
     */
    @RequestMapping("/addFriendRequest")
    @ResponseBody
    public  IWdzlJSONResult addFriendRequest(String myUserId,String friendUserName){
        if(StringUtils.isBlank(myUserId)|| StringUtils.isBlank(friendUserName)){
            return  IWdzlJSONResult.errorMsg("好友信息为空");
        }

        Integer status = userServices.preconditionSearchFriend(myUserId, friendUserName);
        if(status == SearchFriendsStatusEnum.SUCCESS.status){
           userServices.sendFriendRequest(myUserId,friendUserName);
           return IWdzlJSONResult.ok();
        }else {
            String  msg = SearchFriendsStatusEnum.getMsgByKey(status);
            return IWdzlJSONResult.errorMsg(msg);
        }
    }


    @RequestMapping("/getUser")
    public String getUserById(String id, Model model) {
        User user = userServices.getUserById(id);

        model.addAttribute("user", user);
        return "user_list";
    }

}