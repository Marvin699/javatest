package com.hrsystem.controller.admin;

import com.hrsystem.entity.Department;
import com.hrsystem.entity.Employee;
import com.hrsystem.service.DepartmentService;
import com.hrsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "admin/department-list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Department department = departmentService.findById(id);
        List<Employee> employees = employeeService.findByDepartmentId(id);
        model.addAttribute("department", department);
        model.addAttribute("employees", employees);
        return "admin/department-detail";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("department", new Department());
        return "admin/department-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Department department, RedirectAttributes ra) {
        boolean success = departmentService.add(department);
        if (success) {
            ra.addFlashAttribute("message", "添加成功");
        } else {
            ra.addFlashAttribute("error", "添加失败");
        }
        return "redirect:/admin/department/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("department", departmentService.findById(id));
        return "admin/department-form";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Department department, RedirectAttributes ra) {
        boolean success = departmentService.update(department);
        if (success) {
            ra.addFlashAttribute("message", "修改成功");
        } else {
            ra.addFlashAttribute("error", "修改失败");
        }
        return "redirect:/admin/department/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        boolean success = departmentService.delete(id);
        if (success) {
            ra.addFlashAttribute("message", "删除成功");
        } else {
            ra.addFlashAttribute("error", "删除失败");
        }
        return "redirect:/admin/department/list";
    }
}
