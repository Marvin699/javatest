package com.hrsystem.service.impl;

import com.hrsystem.entity.Employee;
import com.hrsystem.entity.LoginUser;
import com.hrsystem.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeMapper.findByUsername(username);
        if (employee == null) {
            throw new UsernameNotFoundException("用户不存在: " + username);
        }
        return new LoginUser(employee);
    }
}
