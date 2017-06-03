package com.zxg.dao;

import com.zxg.mybatis.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by zxg on 2017/5/23.
 * @author zxg
 */
public interface EmployeeMapper {
    public Employee getEmpById(Integer id);
    public void addEmp(Employee employee);
    public void modifyEmp(Employee employee);
    public void deleteEmp(Integer id);

    /**
     * 可以使用 @Param 这个注解对传入的参数进行命名。
     * @param id
     * @param lastName
     * @return
     */
    public Employee getIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    /**
     * 传入数据类型是 map
     * @param map
     * @return
     */
    public Employee getMap(Map<String, Object> map);

    /**
     * 传入的参数是List时，
     * @param list
     * @return
     */
    public Employee getEmpList(List<Integer> list);
}
