package com.clq.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MyFriendsVo {
   private  String  friendUserId;
   private  String  friendUsername;
   private  String  friendFaceImage;
   private  String  friendNickname;
}