package Entity;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class TestMyBatis {
    public static void main(String[] args) throws IOException {
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
        //对应的配置文件和ID名
//        String statement = "personMapper.queryPersonById";
        Person person = sqlSession.selectOne("queryPersonById",1);
        System.out.println(person.toString());

        //需要关闭连接
        sqlSession.close();
    }
}
