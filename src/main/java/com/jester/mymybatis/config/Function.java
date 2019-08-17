package com.jester.mymybatis.config;

import lombok.Data;

/**
 * 用于存放mapper内单个方法的解析结果
 * @author yuxinzh
 * @create 2019/8/17
 */
@Data
public class Function {

    /**
     * sql类型 insert select之类的
     */
    private String sqlType;

    /**
     * 方法名
     */
    private String functionName;

    /**
     * sql内容
     */
    private String sql;

    /**
     * 返回对象类型
     */
    private Object resultType;

    /**
     * 参数类型
     */
    private String parameterType;
}
