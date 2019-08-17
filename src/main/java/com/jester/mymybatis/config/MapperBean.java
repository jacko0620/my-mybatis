package com.jester.mymybatis.config;

import lombok.Data;

import java.util.List;

/**
 * 用于存放解析后的mapper内容
 * @author yuxinzh
 * @create 2019/8/17
 */
@Data
public class MapperBean {

    /**
     * 名称
     */
    private String interfaceName;

    /**
     * 方法列表
     */
    private List<Function> list;
}
