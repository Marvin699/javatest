package com.hrsystem.controller.admin;

import com.hrsystem.entity.OperationLog;
import com.hrsystem.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private LogService logService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<OperationLog> logs = logService.findAll();
        model.addAttribute("logs", logs);
        return "admin/dashboard";
    }
}
