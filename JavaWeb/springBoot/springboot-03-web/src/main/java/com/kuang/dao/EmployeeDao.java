package com.kuang.dao;

import com.kuang.pojo.Department;
import com.kuang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/*
 *@author:C1q
 */

@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer, Employee>();

        employees.put(1001, new Employee(1001, "E-AA", "aa@163.com", 1, new Department(101, "教育部")));
        employees.put(1002, new Employee(1002, "E-BB", "bb@163.com", 1, new Department(102, "市場部")));
        employees.put(1003, new Employee(1003, "E-CC", "cc@163.com", 0, new Department(103, "教研部")));
        employees.put(1004, new Employee(1004, "E-DD", "dd@163.com", 0, new Department(104, "運營部")));
        employees.put(1005, new Employee(1005, "E-EE", "ee@163.com", 1, new Department(105, "後勤部")));
    }

    //主鍵自增
    private static Integer initId = 1006;

    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    //查詢全部員工
    public Collection<Employee> getAll() {
        return employees.values();
    }
    //通過Id查詢員工

    public Employee getEmployById(Integer id) {
        return employees.get(id);
    }

    //刪除員工
    public void detele(Integer id) {
        employees.remove(id);
    }


}
