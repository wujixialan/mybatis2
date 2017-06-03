package com.zxg.test;

import com.zxg.dao.EmployeeMapper;
import com.zxg.mybatis.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxg on 2017/5/23.
 */
/**
 * 1、接口式编程
 * 	原生：		Dao		====>  DaoImpl
 * 	mybatis：	Mapper	====>  xxMapper.xml
 *
 * 2、SqlSession代表和数据库的一次会话；用完必须关闭；
 * 3、SqlSession和connection一样她都是非线程安全。每次使用都应该去获取新的对象。
 * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
 * 		（将接口和xml进行绑定）
 * 		EmployeeMapper empMapper =	sqlSession.getMapper(EmployeeMapper.class);
 * 5、两个重要的配置文件：
 * 		mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息
 * 		sql映射文件：保存了每一个sql语句的映射信息：
 * 					将sql抽取出来。
 *
 * @author zxg
 */
public class MyBatisTest {

    public SqlSessionFactory getSqlSessionFactory() {
		String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new SqlSessionFactoryBuilder().build(inputStream);
	}


    /**
     * 1. 根据 xml 配置文件，创建一个 SqlSessionFactory 对象
     * 2. sql映射文件；配置了每一个sql，以及sql的封装规则等。
     * 3. 将sql映射文件注册在全局配置文件中
     * 4. 写代码：
	 * 		1）、根据全局配置文件得到SqlSessionFactory；
	 * 		2）、使用sqlSession工厂，获取到sqlSession对象使用他来执行增删改查
	 * 			一个sqlSession就是代表和数据库的一次会话，用完关闭
	 * 		3）、使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的。
     */
    @Test
    public void test1() {
        String resouce = "mybatis-config.xml";
        InputStream in = null;
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        try {
            in = Resources.getResourceAsStream(resouce);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            /**
             * 2. 获取 SqlSession 实例，能直接执行已经映射的 sql 语句。
             * sql的唯一标识：statement Unique identifier matching the statement to use.
             * 执行sql要用的参数：parameter A parameter object to pass to the statement.
             */
            sqlSession = sqlSessionFactory.openSession();
            Employee emp = sqlSession.selectOne("com.zxg.mybatis.dao.EmployeeMapper.getEmpById", 1);
            System.out.println("emp = " + emp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test2() {
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            sqlSession = sqlSessionFactory.openSession();
            EmployeeMapper empMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee emp = empMapper.getEmpById(1);
            System.out.println("emp = " + emp);

        } finally {
            sqlSession.close();
        }
    }

    /**
	 * 测试增删改
	 * 1、mybatis允许增删改直接定义以下类型返回值
	 * 		Integer、Long、Boolean、void
	 * 2、我们需要手动提交数据
	 * 		sqlSessionFactory.openSession();===》手动提交
	 * 		sqlSessionFactory.openSession(true);===》自动提交
	 * @throws IOException
	 */
    @Test
    public void testAddEmp() {
        Employee emp = new Employee();
        emp.setLastName("xialan");
        emp.setEmail("15714455@qq.com");
        emp.setGender("0");

        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            /**
             * openSession()： 如果使用这个 openSession()，必须手动提交
             * openSession(boolean var1); 如果使用这个 openSession(true); 可以不用手动提交
             */
            sqlSession = sqlSessionFactory.openSession();
            EmployeeMapper empMapper = sqlSession.getMapper(EmployeeMapper.class);
            empMapper.addEmp(emp);
            System.out.println("emp = " + emp);
            sqlSession.commit();
        } finally {
            /**
             * 关闭 sqlSession
             */
            sqlSession.close();
        }
    }

    @Test
    public void testModifyEmp() {
        Employee emp = new Employee();
        emp.setId(2);
        emp.setLastName("xialan");
        emp.setEmail("11111111@qq.com");
        emp.setGender("0");

        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            /**
             * openSession()： 如果使用这个 openSession()，必须手动提交
             * openSession(boolean var1); 如果使用这个 openSession(true); 可以不用手动提交
             */
            sqlSession = sqlSessionFactory.openSession();
            EmployeeMapper empMapper = sqlSession.getMapper(EmployeeMapper.class);
            empMapper.modifyEmp(emp);
            System.out.println("emp = " + emp);
            sqlSession.commit();
        } finally {
            /**
             * 关闭 sqlSession
             */
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteEmp() {
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            /**
             * openSession()： 如果使用这个 openSession()，必须手动提交
             * openSession(boolean var1); 如果使用这个 openSession(true); 可以不用手动提交
             */
            sqlSession = sqlSessionFactory.openSession();
            EmployeeMapper empMapper = sqlSession.getMapper(EmployeeMapper.class);
            empMapper.deleteEmp(3);
            sqlSession.commit();
        } finally {
            /**
             * 关闭 sqlSession
             */
            sqlSession.close();
        }
    }

    @Test
    public void testGetIdAndLastName() {
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            /**
             * openSession()： 如果使用这个 openSession()，必须手动提交
             * openSession(boolean var1); 如果使用这个 openSession(true); 可以不用手动提交
             */
            sqlSession = sqlSessionFactory.openSession();
            EmployeeMapper empMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = empMapper.getIdAndLastName(2, "xialan");
            System.out.println("employee = " + employee);
            sqlSession.commit();
        } finally {
            /**
             * 关闭 sqlSession
             */
            sqlSession.close();
        }
    }

    @Test
    public void testGetMap() {
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        Map<String, Object> map = new HashMap<>();
        map.put("id", 2);
        map.put("lastName", "xialan");
        try {
            sqlSessionFactory = getSqlSessionFactory();
            /**
             * openSession()： 如果使用这个 openSession()，必须手动提交
             * openSession(boolean var1); 如果使用这个 openSession(true); 可以不用手动提交
             */
            sqlSession = sqlSessionFactory.openSession();
            EmployeeMapper empMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = empMapper.getMap(map);
            System.out.println("employee = " + employee);
            sqlSession.commit();
        } finally {
            /**
             * 关闭 sqlSession
             */
            sqlSession.close();
        }
    }

    @Test
    public void testGetEmpList() {
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        try {
            sqlSessionFactory = getSqlSessionFactory();
            /**
             * openSession()： 如果使用这个 openSession()，必须手动提交
             * openSession(boolean var1); 如果使用这个 openSession(true); 可以不用手动提交
             */
            sqlSession = sqlSessionFactory.openSession();
            EmployeeMapper empMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = empMapper.getEmpList(list);
            System.out.println("employee = " + employee);
            sqlSession.commit();
        } finally {
            /**
             * 关闭 sqlSession
             */
            sqlSession.close();
        }
    }

    @Test
    public void testGetEmpByLastNameLike() {
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        List<Employee> list = new ArrayList<>();

        try {
            sqlSessionFactory = getSqlSessionFactory();
            /**
             * openSession()： 如果使用这个 openSession()，必须手动提交
             * openSession(boolean var1); 如果使用这个 openSession(true); 可以不用手动提交
             */
            sqlSession = sqlSessionFactory.openSession();
            EmployeeMapper empMapper = sqlSession.getMapper(EmployeeMapper.class);
            list = empMapper.getEmpByListNameLike("%xia%");
            System.out.println("list = " + list);
            sqlSession.commit();
        } finally {
            /**
             * 关闭 sqlSession
             */
            sqlSession.close();
        }
    }
}
