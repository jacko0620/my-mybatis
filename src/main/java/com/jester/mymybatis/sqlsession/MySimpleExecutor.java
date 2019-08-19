package com.jester.mymybatis.sqlsession;

import com.alibaba.fastjson.JSON;
import com.jester.mymybatis.BaseFile;
import com.jester.mymybatis.MyConfiguration;
import com.jester.mymybatis.mapping.MyMappedStatement;

import java.sql.Connection;

/**
 * @author yuxinzh
 * @create 2019/8/19
 */
public class MySimpleExecutor extends BaseFile implements MyExecutor {

    private MyConfiguration configuration;

    @Override
    public <T> T query(MyMappedStatement statement, Object parameter) {
        Connection connection = createConnection();
        try {
            return null;
        } catch (Exception e) {
            logger.error("query error,[statement]->{},[parameter]->{}", JSON.toJSONString(statement),JSON.toJSONString(parameter),e);
            throw new RuntimeException(e);
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
}
