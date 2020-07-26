package com.kuang.dao;

import com.kuang.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/*
 *@author:C1q
 */

@Repository
public class DepartmentDao {

    private  static Map<Integer, Department> departments =null;

    //
    static {
        departments = new HashMap<Integer,Department>();
        departments.put(101,new Department(101,"教育部"));
        departments.put(102,new Department(102,"市場部"));
        departments.put(103,new Department(103,"教研部"));
        departments.put(104,new Department(104,"運營部"));
        departments.put(105,new Department(105,"後勤部"));
    }
    public Collection<Department>getDepartments(){
        return departments.values();
    }

    public  Department getDepartmentById(Integer id){
        return departments.get(id);
    }

}
