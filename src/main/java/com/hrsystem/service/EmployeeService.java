package com.hrsystem.service;

import com.hrsystem.entity.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(Integer id);
    List<Employee> findByDepartmentId(Integer departmentId);
    List<Employee> search(String empNo, String gender, String position);
    boolean add(Employee employee);
    boolean update(Employee employee);
    boolean delete(Integer id);
}
