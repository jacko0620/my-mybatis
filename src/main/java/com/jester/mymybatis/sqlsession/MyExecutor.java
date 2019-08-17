package com.jester.mymybatis.sqlsession;

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
    <T> T select(String statement,Object parameter);

    /**
     * 插入
     * @param statement
     * @param parameter
     */
    void insert(String statement,Object parameter);

    /**
     * 更新
     * @param statement
     * @param parameter
     */
    void update(String statement,Object parameter);

    /**
     * 删除
     * @param statement
     * @param parameter
     */
    void delete(String statement,Object parameter);
}
