# 人事管理系统

基于SpringBoot的企业人事管理系统，包含后台管理端和前台员工端。

## 技术栈

- **后端框架**：SpringBoot 2.7.18
- **持久层**：MyBatis
- **页面模板**：Thymeleaf
- **前端框架**：Bootstrap 5
- **安全框架**：Spring Security
- **数据库**：MySQL 8.0
- **JDK版本**：JDK 8
- **构建工具**：Maven

## 如何运行项目

### 第一步：配置MySQL数据库

1. 启动MySQL服务
```bash
mysql -u root -p
```

2. 执行SQL脚本初始化数据库
```bash
mysql -u root -p < sql/init.sql
```
或者复制 `sql/init.sql` 的内容在MySQL命令行中执行。

3. 修改数据库密码
打开 `src/main/resources/application.yml`，把 `password: root` 改成你MySQL的实际密码。

### 第二步：用IDEA打开项目

1. 打开 IntelliJ IDEA
2. 选择 `File` → `Open` → 选择 `hr-system` 文件夹
3. 等待Maven下载依赖（右下角进度条）
4. 找到 `HrSystemApplication.java`，右键选择 `Run`

### 第三步：访问系统

浏览器打开 http://localhost:8080

### 测试账号

| 角色 | 用户名 | 密码 |
|------|--------|------|
| 管理员 | admin | 123456 |
| 普通员工 | emp001 | 123456 |
| 普通员工 | emp002 | 123456 |
| 普通员工 | emp003 | 123456 |
| 普通员工 | emp004 | 123456 |

## 项目架构

```
hr-system/
├── pom.xml                          # Maven配置（依赖管理）
├── sql/init.sql                     # 数据库初始化脚本
└── src/main/
    ├── java/com/hrsystem/
    │   ├── HrSystemApplication.java # 启动类
    │   ├── entity/                  # 实体类（对应数据库表）
    │   │   ├── Department.java      # 部门实体
    │   │   ├── Employee.java        # 员工实体
    │   │   ├── LoginUser.java       # 登录用户（Spring Security用）
    │   │   └── OperationLog.java    # 操作日志实体
    │   ├── mapper/                  # 数据访问层（MyBatis）
    │   │   ├── DepartmentMapper.java
    │   │   ├── EmployeeMapper.java
    │   │   └── OperationLogMapper.java
    │   ├── service/                 # 业务逻辑层
    │   │   ├── DepartmentService.java
    │   │   ├── EmployeeService.java
    │   │   ├── LogService.java
    │   │   └── impl/               # 实现类
    │   ├── controller/             # 控制器（处理请求）
    │   │   ├── LoginController.java
    │   │   ├── admin/              # 管理后台
    │   │   └── employee/           # 员工端
    │   ├── config/
    │   │   └── SecurityConfig.java # Spring Security配置
    │   └── aop/
    │       └── LogAspect.java      # AOP日志切面
    └── resources/
        ├── application.yml         # 应用配置
        ├── mapper/                 # MyBatis XML映射文件
        └── templates/              # Thymeleaf页面
            ├── login.html
            ├── admin/              # 管理后台页面
            └── employee/           # 员工端页面
```

## 功能对照

| 需求编号 | 需求描述 | 实现文件 |
|----------|----------|----------|
| (1) | 员工表格显示所有员工信息 | `admin/employee-list.html` + `EmployeeController.java` |
| (2) | 员工的添加、编辑、删除 | `admin/employee-form.html` + `EmployeeController.java` |
| (3) | 根据工号、性别、职位多条件查询 | `EmployeeMapper.xml` 的 `search` 方法 |
| (4) | 部门表格显示所有部门信息 | `admin/department-list.html` |
| (5) | 点击部门名称查看该部门员工 | `admin/department-detail.html` |
| (6) | 部门的添加、编辑、删除 | `admin/department-form.html` |
| (7) | 只有管理员可以进入管理端 | `SecurityConfig.java` 配置 `/admin/**` 需要ADMIN角色 |
| (8) | AOP记录删除操作日志 | `LogAspect.java` 拦截删除操作并记录日志 |
| (9) | 员工查看本部门同事信息 | `employee/dashboard.html` + `EmployeeController.java` |

## 数据库设计

### department 部门表
| 字段 | 类型 | 说明 |
|------|------|------|
| id | INT | 部门ID（主键） |
| name | VARCHAR(50) | 部门名称 |
| code | VARCHAR(20) | 部门编号 |
| create_time | DATETIME | 创建时间 |

### employee 员工表
| 字段 | 类型 | 说明 |
|------|------|------|
| id | INT | 员工ID（主键） |
| name | VARCHAR(50) | 姓名 |
| emp_no | VARCHAR(20) | 工号 |
| gender | VARCHAR(4) | 性别 |
| position | VARCHAR(50) | 职位 |
| birth_date | DATE | 出生日期 |
| department_id | INT | 部门ID（外键） |
| username | VARCHAR(50) | 登录用户名 |
| password | VARCHAR(100) | 登录密码（BCrypt加密） |
| role | VARCHAR(20) | 角色：ADMIN/EMPLOYEE |
| create_time | DATETIME | 创建时间 |

### operation_log 操作日志表
| 字段 | 类型 | 说明 |
|------|------|------|
| id | INT | 日志ID（主键） |
| username | VARCHAR(50) | 操作人 |
| operation | VARCHAR(100) | 操作内容 |
| method | VARCHAR(200) | 请求方法 |
| params | TEXT | 请求参数 |
| ip | VARCHAR(50) | IP地址 |
| create_time | DATETIME | 操作时间 |

## 常见问题

### Q1: Maven下载依赖很慢？
配置阿里云镜像，编辑 `~/.m2/settings.xml`，在 `<mirrors>` 中添加：
```xml
<mirror>
    <id>aliyun</id>
    <mirrorOf>central</mirrorOf>
    <name>Aliyun Maven</name>
    <url>https://maven.aliyun.com/repository/public</url>
</mirror>
```

### Q2: 数据库连接失败？
检查 `application.yml` 中的数据库用户名密码是否正确，MySQL服务是否启动。

### Q3: 端口被占用？
修改 `application.yml` 中的 `server.port` 改为其他端口。
