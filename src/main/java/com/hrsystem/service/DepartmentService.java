package com.hrsystem.service;

import com.hrsystem.entity.Department;
import java.util.List;

public interface DepartmentService {
    List<Department> findAll();
    Department findById(Integer id);
    boolean add(Department department);
    boolean update(Department department);
    boolean delete(Integer id);
}
