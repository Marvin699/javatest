package com.hrsystem.mapper;

import com.hrsystem.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> findAll();
    Employee findById(Integer id);
    Employee findByUsername(String username);
    List<Employee> findByDepartmentId(Integer departmentId);
    List<Employee> search(@Param("empNo") String empNo, @Param("gender") String gender, @Param("position") String position);
    int insert(Employee employee);
    int update(Employee employee);
    int deleteById(Integer id);
}
