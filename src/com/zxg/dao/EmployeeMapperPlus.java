package com.zxg.dao;

import com.zxg.mybatis.Employee;

/**
 * Created by zxg on 2017/6/3.
 */
public interface EmployeeMapperPlus {
    public Employee getEmpById(Integer id);
}
