package Entity;

import java.util.List;

//操作MyBatis接口
public interface PersonMapper {
    /*
    *1.方法名和mapper.xml文件中标签的id值相同
    *2.方法的输入参数和mapper.xml文件中标签的parameterTypel类型一致
    * 3.方法的返回值和mapper.xml文件中标签的resultType类型一致
    */
    //查询一个person
    Person queryPersonById(int id);
    //查询多个person
    List<Person> quaryAllPerson();
    // 增加
    void addPerson(Person person);
    //修改
    void updatePersonById(Person person);
    //删除
    void deletePersonById(int id);

    //类型转换器查询学生
    Person queryPersonByIdWithConverter(int id);

    //关联查询一对一
    StudentBusiness quaryStudentByNoWithOO(int id);

    //关联查询一对一的另一种写法
    Student quaryStudentByNoWithOO2(int id);
}
