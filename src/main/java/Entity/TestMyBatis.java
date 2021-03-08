package Entity;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class TestMyBatis {

    //查找数据方法
    public static Person quaryById() {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("conf.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //sqlSessionFactory - connection
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //sqlSession - connection
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //对应的配置文件和ID名
        String statement = "personMapper.queryPersonById";
        Person person = sqlSession.selectOne("queryPersonById",1);
        //动态代理查询的写法
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        personMapper.queryPersonById(1);
        System.out.println(person.toString());
        sqlSession.close();
        return person;
    }

    //插入数据方法
    public static void addPerson() {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("conf.xml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Person person = new Person();
        person.setId(2);
        person.setName("郝燕挺");
        person.setAge(28);
//        //返回的数字是几，代表增加了几条数据
//        int success = sqlSession.insert("addPerson",person);
//        if (success > 0) {
//            System.out.println("插入操作成功" + success);
//        }

        //动态代理的写法
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        personMapper.addPerson(person);
        //因为配置的使用jdbc方式，需要手动提交事务才行,不加手动提交看不到数据
        sqlSession.commit();

        //需要关闭连接
        sqlSession.close();
    }

    //更新数据方法
    public static void updatePerson() {
        //加载myBatis配置文件（为了访问数据库）
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("conf.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //sqlSessionFactory - connection
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //sqlSession - connection
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Person person = quaryById();
        person.setAge(18);
//        sqlSession.update("updatePersonById",person);
        //动态代理的写法
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        personMapper.updatePersonById(person);

        //千万别忘记commmit方法
        sqlSession.commit();
        sqlSession.close();
    }

    public static void deletePeronById() {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("conf.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //sqlSessionFactory - connection
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //sqlSession - connection
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //对应的配置文件和ID名
//        int count = sqlSession.delete("deletePersonById",2);

        //动态代理的写法
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        personMapper.deletePersonById(2);
        sqlSession.commit();
//        System.out.println("删除"+count+"个数据");
        sqlSession.close();
    }

    public static void quaryAllPerson() {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("conf.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //sqlSessionFactory - connection
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //sqlSession - connection
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //对应的配置文件和ID名
//        String statement = "personMapper.queryPersonById";
        //查询所有的，要使用selectList
//        List<Person> person = sqlSession.selectList("quaryAllPerson");
        //动态代理的写法
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        List<Person> person = personMapper.quaryAllPerson();
        System.out.println(person.toString());

        sqlSession.close();
    }
    //使用转换器的方法查找
    public static void quaryPersonByIdWithConverter() {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("conf.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //sqlSessionFactory - connection
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //sqlSession - connection
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //对应的配置文件和ID名
//        String statement = "personMapper.queryPersonById";
        //查询所有的，要使用selectList
//        List<Person> person = sqlSession.selectList("quaryAllPerson");
        //动态代理的写法
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        Person person = personMapper.queryPersonByIdWithConverter(1);
        System.out.println(person.toString());

        sqlSession.close();
    }

    public static Person quaryStudentByIdWithOO() {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("conf.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //sqlSessionFactory - connection
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //sqlSession - connection
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //对应的配置文件和ID名
        String statement = "personMapper.queryPersonById";
        Person person = sqlSession.selectOne("queryPersonById",1);
        //动态代理查询的写法
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        StudentBusiness studentBusiness = personMapper.quaryStudentByNoWithOO(2);
        System.out.println(studentBusiness.toString());
        sqlSession.close();
        return person;
    }



    public static void main(String[] args) {
//        quaryAllPerson();
//        deletePeronById();
//        quaryAllPerson();

//        addPerson();
//        updatePerson();
//        quaryAllPerson();
//        quaryPersonByIdWithConverter();
        quaryStudentByIdWithOO();
    }
}
