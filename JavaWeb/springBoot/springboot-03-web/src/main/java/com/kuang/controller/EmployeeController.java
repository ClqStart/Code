package com.kuang.controller;

import com.kuang.dao.DepartmentDao;
import com.kuang.dao.EmployeeDao;
import com.kuang.pojo.Department;
import com.kuang.pojo.Employee;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model mode) {
        Collection<Employee> employees = employeeDao.getAll();
        mode.addAttribute("emps",employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public  String  toAddpage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();

        model.addAttribute("departments",departments);
        return "emp/add";
    }
    @PostMapping("/emp")
    public  String  toAddEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //员工的修改页面

    @GetMapping("/emp/{id}")
    public  String  toUpdateEmp(@PathVariable("id") Integer id, Model model){
        Employee employById = employeeDao.getEmployById(id);
        model.addAttribute("emp",employById);

        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/update";
    }

    @PostMapping("/updateEmp")
    public  String  updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //删除员工
    @GetMapping("/deteleEmp/{id}")
    public  String   deteleEmp(@PathVariable("id") Integer id){
        employeeDao.detele(id);
        return "redirect:/emps";
    }

    //推出登陆

    @GetMapping("/SighOut")
    public  String  SighOut(HttpSession session){
        session.removeAttribute("loginUse");
        return "redirect:/index.html";

    }


}
