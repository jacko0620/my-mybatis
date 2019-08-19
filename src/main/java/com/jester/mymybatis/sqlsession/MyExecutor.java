package com.jester.mymybatis.sqlsession;

import com.jester.mymybatis.mapping.MyMappedStatement;

/**
 *
 * @author yuxinzh
 * @create 2019/8/17
 */
public interface MyExecutor {

    /**
     * 查询
     * @param statement
     * @param parameter
     * @param <T>
     * @return
     */
    <T> T query(MyMappedStatement statement,Object parameter);

    /**
     * 插入
     * @param statement
     * @param parameter
     */
    void insert(MyMappedStatement statement,Object parameter);

    /**
     * 更新
     * @param statement
     * @param parameter
     */
    void update(MyMappedStatement statement,Object parameter);

    /**
     * 删除
     * @param statement
     * @param parameter
     */
    void delete(MyMappedStatement statement,Object parameter);

    // todo createCacheKey
}
