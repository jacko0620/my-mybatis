package com.jester.mymybatis.mapping;

import lombok.Data;

import java.util.UUID;

/**
 * @author yuxinzh
 * @create 2019/8/19
 */
@Data
public class MyMappedStatement {

    /**
     * statementId,拼cacheKey用
     */
    private String id;

    private MySqlSource sqlSource;

    public MyMappedStatement() {
        this.id = UUID.randomUUID().toString().replaceAll("\\-","");
    }
}
