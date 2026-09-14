package com.hrsystem.service.impl;

import com.hrsystem.entity.Employee;
import com.hrsystem.mapper.EmployeeMapper;
import com.hrsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<Employee> findAll() {
        return employeeMapper.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        return employeeMapper.findById(id);
    }

    @Override
    public List<Employee> findByDepartmentId(Integer departmentId) {
        return employeeMapper.findByDepartmentId(departmentId);
    }

    @Override
    public List<Employee> search(String empNo, String gender, String position) {
        return employeeMapper.search(empNo, gender, position);
    }

    @Override
    public boolean add(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        if (employee.getRole() == null || employee.getRole().isEmpty()) {
            employee.setRole("EMPLOYEE");
        }
        return employeeMapper.insert(employee) > 0;
    }

    @Override
    public boolean update(Employee employee) {
        if (employee.getPassword() != null && !employee.getPassword().isEmpty()) {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        } else {
            employee.setPassword(null);
        }
        return employeeMapper.update(employee) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return employeeMapper.deleteById(id) > 0;
    }
}
