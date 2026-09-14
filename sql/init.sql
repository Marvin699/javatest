-- 创建数据库
CREATE DATABASE IF NOT EXISTS hr_system DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE hr_system;

-- 部门表
DROP TABLE IF EXISTS department;
CREATE TABLE department (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '部门ID',
    name VARCHAR(50) NOT NULL COMMENT '部门名称',
    code VARCHAR(20) NOT NULL UNIQUE COMMENT '部门编号',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- 员工表
DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '员工ID',
    name VARCHAR(50) NOT NULL COMMENT '姓名',
    emp_no VARCHAR(20) NOT NULL UNIQUE COMMENT '工号',
    gender VARCHAR(4) NOT NULL COMMENT '性别',
    position VARCHAR(50) NOT NULL COMMENT '职位',
    birth_date DATE COMMENT '出生日期',
    department_id INT NOT NULL COMMENT '部门ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '登录用户名',
    password VARCHAR(100) NOT NULL COMMENT '登录密码',
    role VARCHAR(20) NOT NULL DEFAULT 'EMPLOYEE' COMMENT '角色: ADMIN/EMPLOYEE',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工表';

-- 日志表
DROP TABLE IF EXISTS operation_log;
CREATE TABLE operation_log (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '日志ID',
    username VARCHAR(50) NOT NULL COMMENT '操作人',
    operation VARCHAR(100) NOT NULL COMMENT '操作内容',
    method VARCHAR(200) COMMENT '请求方法',
    params TEXT COMMENT '请求参数',
    ip VARCHAR(50) COMMENT 'IP地址',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志表';

-- 插入测试数据
-- 管理员账号: admin / 123456
-- 员工账号: emp001 / 123456

INSERT INTO department (name, code) VALUES ('技术部', 'TECH'), ('人事部', 'HR'), ('市场部', 'MARKET');

-- 密码都是123456 (BCrypt加密)
INSERT INTO employee (name, emp_no, gender, position, birth_date, department_id, username, password, role) VALUES
('张三', 'EMP001', '男', '技术总监', '1990-05-15', 1, 'admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'ADMIN'),
('李四', 'EMP002', '男', 'Java工程师', '1995-08-20', 1, 'emp001', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'EMPLOYEE'),
('王五', 'EMP003', '女', '人事经理', '1992-03-10', 2, 'emp002', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'EMPLOYEE'),
('赵六', 'EMP004', '男', '前端工程师', '1997-11-25', 1, 'emp003', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'EMPLOYEE'),
('钱七', 'EMP005', '女', '市场专员', '1996-07-08', 3, 'emp004', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'EMPLOYEE');
