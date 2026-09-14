package com.hrsystem.service;

import com.hrsystem.entity.OperationLog;
import java.util.List;

public interface LogService {
    List<OperationLog> findAll();
    void addLog(OperationLog log);
}
