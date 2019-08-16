package com.jester.mymybatis.service;


import com.jester.mymybatis.vo.UserVO;

/**
 * @author yuxinzh
 * @create 2019/5/27
 */
public interface IUserService {

    UserVO getUserById(String id);
}
