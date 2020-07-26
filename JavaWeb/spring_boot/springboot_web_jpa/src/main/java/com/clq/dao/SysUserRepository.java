package com.clq.dao;

import com.clq.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 *@author:C1q
 */
public interface SysUserRepository extends JpaRepository<SysUser,Integer> {
}
