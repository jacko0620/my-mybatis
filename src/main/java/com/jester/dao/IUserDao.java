package com.jester.dao;

import com.jester.vo.UserVO;
import org.springframework.stereotype.Repository;


/**
 * @author yuxinzh
 * @create 2019/5/27
 */
@Repository
public interface IUserDao {

    UserVO getUserById(String id);
}
