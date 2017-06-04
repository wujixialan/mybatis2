package com.zxg.dao;

import com.zxg.mybatis.Employee;

import java.util.List;

/**
 * Created by zxg on 2017/6/3.
 */
public interface EmployeeMapperPlus {
    public Employee getEmpById(Integer id);

    public Employee getEmpAndDept(Integer id);

    public Employee getEmpByIdStep(Integer id);

    public List<Employee> getEmpsByDeptId(Integer id);
}
