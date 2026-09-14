package com.hrsystem.service.impl;

import com.hrsystem.entity.OperationLog;
import com.hrsystem.mapper.OperationLogMapper;
import com.hrsystem.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private OperationLogMapper logMapper;

    @Override
    public List<OperationLog> findAll() {
        return logMapper.findAll();
    }

    @Override
    public void addLog(OperationLog log) {
        logMapper.insert(log);
    }
}
