package com.jester.mymybatis.dao;

import com.jester.mymybatis.vo.UserVO;
import org.springframework.stereotype.Repository;


/**
 * @author yuxinzh
 * @create 2019/5/27
 */
@Repository
public interface IUserDao {

    UserVO getUserById(String id);
}
