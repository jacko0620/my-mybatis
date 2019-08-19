package com.jester.mymybatis.mapping;

import lombok.Data;

import java.util.Map;

/**
 * 实际应该是接口
 * 实现类分为DynamicSqlSource，ProviderSqlSource，StaticSqlSource
 * 这里简化
 * @author yuxinzh
 * @create 2019/8/19
 */
@Data
public class MySqlSource {

    private String sql;

    private Map<String,Object> additionalParameters;

    public MySqlSource(String sql, Map<String, Object> additionalParameters) {
        this.sql = sql;
        this.additionalParameters = additionalParameters;
    }

    public MySqlSource() {
    }

    public MySqlBound getBoundSql(){
        return new MySqlBound(this.sql,this.additionalParameters);
    }
}
