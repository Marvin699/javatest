package com.hrsystem.mapper;

import com.hrsystem.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    List<Department> findAll();
    Department findById(Integer id);
    Department findByCode(String code);
    int insert(Department department);
    int update(Department department);
    int deleteById(Integer id);
}
