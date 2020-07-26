package com.clq.mybatisp.mapper;

/*
 *@author:C1q
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clq.mybatisp.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public  interface  UserMapper extends BaseMapper<User>{
    User findById(Long id);
}