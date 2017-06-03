package com.zxg.test;

import com.zxg.dao.DepartmentMapper;
import com.zxg.dao.EmployeeMapper;
import com.zxg.dao.EmployeeMapperPlus;
import com.zxg.mybatis.Department;
import com.zxg.mybatis.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zxg on 2017/6/3.
 */
public class EmployeePlus {
    private SqlSessionFactory sqlSessionFactory = null;

    @BeforeEach
    public void init() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testGetEmpById() {
        SqlSession sqlSession = null;
        try {
            sqlSession = this.sqlSessionFactory.openSession(true);
            EmployeeMapperPlus employeeMapperPlus = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = employeeMapperPlus.getEmpById(1);
            System.out.println("employee = " + employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testGetEmpAndDept() {
        SqlSession sqlSession = null;
        try {
            sqlSession = this.sqlSessionFactory.openSession(true);
            EmployeeMapperPlus employeeMapperPlus = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = employeeMapperPlus.getEmpAndDept(1);
            System.out.println("employee = " + employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testGetEmpStepAndDept() {
        SqlSession sqlSession = null;
        try {
            sqlSession = this.sqlSessionFactory.openSession(true);
            EmployeeMapperPlus employeeMapperPlus = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = employeeMapperPlus.getEmpByIdStep(1);
//            System.out.println("employee = " + employee);
            System.out.println(employee.getLastName());
            System.out.println(employee.getDept());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testGetDeptByIdPlus() {
        SqlSession sqlSession = null;
        try {
            sqlSession = this.sqlSessionFactory.openSession(true);
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
            Department department = departmentMapper.getDeptByIdPlus(2);
            System.out.println("department = " + department);
        } finally {
            sqlSession.close();
        }
    }
}
