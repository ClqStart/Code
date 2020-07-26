package com.clq.mybatisp.pojo;

/*
 *@author:C1q
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
public class User {

    @TableId(value = "id",type= IdType.AUTO)//指定主键的字段名
    private Long id;

    @TableField("user_name")//指定对应字段名
    private String userName;

    private String password;
    @TableField("NAME")
    private String name;
    @TableField("AGE")
    private Integer age;
    @TableField("EMAIL")
    private String email;
    @TableField("birthday")
    private LocalDateTime birthday;

}
