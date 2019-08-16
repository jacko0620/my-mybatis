package com.jester.service.impl;

import com.jester.dao.IUserDao;
import com.jester.service.IUserService;
import com.jester.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuxinzh
 * @create 2019/5/27
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public UserVO getUserById(String id) {
        return userDao.getUserById(id);
    }
}
