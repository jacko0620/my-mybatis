package com.jester.mymybatis.mapping;

import lombok.Data;

import java.util.Map;

/**
 * @author yuxinzh
 * @create 2019/8/19
 */
@Data
public class MySqlBound {

    private String sql;

    /**
     * 参数
     */
    private Map<String,Object> additionalParameters;

    public MySqlBound(String sql, Map<String, Object> additionalParameters) {
        this.sql = sql;
        this.additionalParameters = additionalParameters;
    }

    public MySqlBound() {
    }
}
