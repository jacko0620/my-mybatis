package com.jester.mymybatis.sqlsession;

import com.alibaba.fastjson.JSON;
import com.jester.mymybatis.BaseFile;
import com.jester.mymybatis.MyConfiguration;
import com.jester.mymybatis.mapping.MyMappedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yuxinzh
 * @create 2019/8/19
 */
public class MySimpleExecutor extends BaseFile implements MyExecutor {

    private MyConfiguration configuration;

    @Override
    public <T> T query(MyMappedStatement statement, Object parameter) {
        Connection connection = createConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(statement.getSqlSource().getSql());
            preparedStatement.setString(1,parameter.toString());
            resultSet = preparedStatement.executeQuery();
            logger.info("query success,[resultSet]->{}",JSON.toJSONString(resultSet));
            return null;
        } catch (Exception e) {
            logger.error("query error,[statement]->{},[parameter]->{}", JSON.toJSONString(statement),JSON.toJSONString(parameter),e);
            throw new RuntimeException(e);
        } finally {
            close(resultSet,preparedStatement,connection);
        }
    }

    @Override
    public void insert(MyMappedStatement statement, Object parameter) {

    }

    @Override
    public void update(MyMappedStatement statement, Object parameter) {

    }

    @Override
    public void delete(MyMappedStatement statement, Object parameter) {

    }

    /**
     * 创建数据库链接
     * @return
     */
    private Connection createConnection(){
        try{
            Connection connection = configuration.build();
            connection.setAutoCommit(false);
            logger.info("create connection success!");
            return connection;
        } catch (Exception e) {
            logger.error("create connection false!",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭资源
     * @param rs
     * @param pst
     * @param cn
     */
    private void close(ResultSet rs,PreparedStatement pst,Connection cn){
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        } catch (SQLException e) {
            logger.error("close fail ",e);
            // 算了不抛
        }
    }

    /**
     * 事务提交
     * @param cn
     */
    private void commit(Connection cn) {
        try {
            cn.commit();
        } catch (SQLException e) {
            logger.error("commit fail ",e);
            rollback(cn);
            throw new RuntimeException(e);
        }
    }

    private void rollback(Connection cn) {
        try {
            cn.rollback();
        } catch (SQLException e) {
            logger.error("rollback fail ",e);
            throw new RuntimeException(e);
        }
    }


}
