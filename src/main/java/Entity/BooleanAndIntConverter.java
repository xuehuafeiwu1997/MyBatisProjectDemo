package Entity;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//范型中存放的是java中需要转换的类型
//需求是将java中boolean和数据库中的number类型进行转化     true代表1，false代表0
public class BooleanAndIntConverter extends BaseTypeHandler<Boolean> {

    /**
     *
     * @param ps ：preparedStatement
     * @param i:preparedStatement对象操作参数的位置
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    /*
    set  是java->DB 转换
    get 是DB向java转换
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
        if (parameter) {
            ps.setInt(i,1);
        } else {
            ps.setInt(i,0);
        }
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int setNum = rs.getInt(columnName);
        return setNum == 1 ? true : false;
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int setNum = rs.getInt(columnIndex);
        return setNum == 1 ? true : false;
    }

    @Override
    public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int setNum = cs.getInt(columnIndex);
        return setNum == 1 ? true : false;
    }
}
