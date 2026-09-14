package com.hrsystem.controller.employee;

import com.hrsystem.entity.Employee;
import com.hrsystem.entity.LoginUser;
import com.hrsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal LoginUser loginUser, Model model) {
        Employee currentEmployee = loginUser.getEmployee();
        List<Employee> colleagues = employeeService.findByDepartmentId(currentEmployee.getDepartmentId());
        model.addAttribute("colleagues", colleagues);
        model.addAttribute("currentEmployee", currentEmployee);
        return "employee/dashboard";
    }
}
