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

@Controller("adminEmployeeController")
@RequestMapping("/admin/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "admin/employee-list";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String empNo,
                         @RequestParam(required = false) String gender,
                         @RequestParam(required = false) String position,
                         Model model) {
        List<Employee> employees = employeeService.search(empNo, gender, position);
        model.addAttribute("employees", employees);
        model.addAttribute("empNo", empNo);
        model.addAttribute("gender", gender);
        model.addAttribute("position", position);
        return "admin/employee-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.findAll());
        return "admin/employee-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Employee employee, RedirectAttributes ra) {
        boolean success = employeeService.add(employee);
        if (success) {
            ra.addFlashAttribute("message", "添加成功");
        } else {
            ra.addFlashAttribute("error", "添加失败");
        }
        return "redirect:/admin/employee/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        model.addAttribute("departments", departmentService.findAll());
        return "admin/employee-form";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Employee employee, RedirectAttributes ra) {
        boolean success = employeeService.update(employee);
        if (success) {
            ra.addFlashAttribute("message", "修改成功");
        } else {
            ra.addFlashAttribute("error", "修改失败");
        }
        return "redirect:/admin/employee/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        boolean success = employeeService.delete(id);
        if (success) {
            ra.addFlashAttribute("message", "删除成功");
        } else {
            ra.addFlashAttribute("error", "删除失败");
        }
        return "redirect:/admin/employee/list";
    }
}
