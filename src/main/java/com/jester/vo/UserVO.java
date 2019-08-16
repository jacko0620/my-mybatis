package com.jester.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yuxinzh
 * @create 2019/8/15
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 997156447143985739L;

    private String id;

    private String username;

    private String password;
}
