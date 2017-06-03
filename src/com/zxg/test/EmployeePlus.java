package com.zxg.test;

import com.zxg.dao.EmployeeMapper;
import com.zxg.dao.EmployeeMapperPlus;
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
}
