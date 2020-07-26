package com.clq.vo;

import lombok.Data;
import lombok.ToString;



@Data
@ToString
public class FriendsRequestVo {

    private String sendUserId;
    private String sendUsername;
    private String sendFaceImage;
    private String sendNickname;


}