package com.hrsystem.mapper;

import com.hrsystem.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperationLogMapper {
    List<OperationLog> findAll();
    int insert(OperationLog log);
}
