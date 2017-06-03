package com.zxg.dao;

import com.zxg.mybatis.Department;

/**
 * Created by zxg on 2017/6/3.
 */
public interface DepartmentMapper {
    public Department getDeptById(Integer id);

    public Department getDeptByIdPlus(Integer id);
}
