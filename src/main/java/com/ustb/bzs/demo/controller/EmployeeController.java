package com.ustb.bzs.demo.controller;

import com.ustb.bzs.demo.dao.DepartmentDao;
import com.ustb.bzs.demo.dao.EmployeeDao;
import com.ustb.bzs.demo.entities.Department;
import com.ustb.bzs.demo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.text.normalizer.NormalizerBase;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employeeDaoAll = employeeDao.getAll();

        //System.out.println("emps请求");
        //放在请求域中 map model modelmap
        model.addAttribute("emps",employeeDaoAll);
        //thymeleaf 自动拼串
        //classpath:/templates/***.html
        return "emps/list";
    }

    //来到添加员工页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //查出所有部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emps/add";
    }


    @PostMapping("/emp")
    //springMVC 自动将请求数据和入参对象的属性一一绑定
    public String add(Employee employee){

        //System.out.println("111"+employee);
        employeeDao.save(employee);
        //redirect forward
        ///来到员工list页面
        return "redirect:/emps";
    }

    //来到修改页面 查出员工 页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){

        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        //查出所有部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);

        //添加修改二合一
        return "emps/add";
    }

    //修改员工信息  需要提交id
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        //System.out.println(employee);
        employeeDao.save(employee);
        //修改完成来到员工页面
        return "redirect:/emps";
    }

    //删除员工
    @PostMapping("/deleemp/{id}")
    // @DeleteMapping("/emp/{id}")
    public String deletedEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
