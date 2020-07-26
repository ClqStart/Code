package com.clq.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/*
 *@author:C1q
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity//告诉JPA这是一个实体类与数据表关联的类
@Table(name = "sys_user")
public class SysUser {
    //主键id，数据库字段为：sys_user.id
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略，这里采用自增列
    private Long id;

    //登录名，数据库字段为：sys_user.login_name
    @Column(name = "login_name", length = 64)
    private String loginName;

    //用户名，数据库字段为：sys_user.name
    @Column//默认不写name属性则表示属性名和字段名同名
    private String name;

    //密码，数据库字段为：sys_user.password
    @Column
    private String password;

    //性别，数据库字段为：sys_user.sex
    @Column
    private Integer sex;

    //年龄，数据库字段为：sys_user.age
    @Column
    private Integer age;

    //手机号，数据库字段为：sys_user.phone
    @Column
    private String phone;

    //用户类别，数据库字段为：sys_user.user_type
    @Column(name = "user_type")
    private Integer userType;

    //用户状态，数据库字段为：sys_user.status
    @Column
    private Integer status;

    //删除标记，数据库字段为：sys_user.del_flag
    @Column(name = "del_flag")
    private Integer delFlag;

    //更新时间，数据库字段为：sys_user.update_time
    @Column(name = "update_time")
    private Date updateTime;

    //创建时间，数据库字段为：sys_user.create_time
    @Column(name = "create_time")
    private Date createTime;
}