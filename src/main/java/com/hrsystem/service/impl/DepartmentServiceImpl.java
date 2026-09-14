package com.hrsystem.service.impl;

import com.hrsystem.entity.Department;
import com.hrsystem.mapper.DepartmentMapper;
import com.hrsystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> findAll() {
        return departmentMapper.findAll();
    }

    @Override
    public Department findById(Integer id) {
        return departmentMapper.findById(id);
    }

    @Override
    public boolean add(Department department) {
        return departmentMapper.insert(department) > 0;
    }

    @Override
    public boolean update(Department department) {
        return departmentMapper.update(department) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return departmentMapper.deleteById(id) > 0;
    }
}
