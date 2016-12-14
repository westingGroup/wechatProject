package com.infosys.basic.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.basic.dao.IUserDao;
import com.infosys.basic.entity.User;

@Service("userService")
@Transactional
public class UserService implements IUserService {
    @Inject
    private IUserDao userDao;

    @Override
    public void add(User user) {
        User u = this.loadByUsername(user.getUsername());
        if (u != null)
            throw new RuntimeException("用户名已经存在");
        user.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL);
        userDao.add(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public User load(int id) {
        return userDao.load(id);
    }

    @Override
    public User loadByUsername(String username) {
        return userDao.loadByUsername(username);
    }

    @Override
    public User loadByOpenid(String openid) {
        return userDao.loadByOpenId(openid);
    }

    @Override
    public List<User> list() {
        return userDao.list();
    }

    @Override
    public User login(String username, String password) {
        User u = this.loadByUsername(username);
        if (u == null)
            throw new RuntimeException("用户名不存在!");
        if (!password.equals(u.getPassword()))
            throw new RuntimeException("用户密码不正确！");
        if (u.getStatus() == 0)
            throw new RuntimeException("用户已经停用!");
        return u;
    }

    @Override
    public List<User> listDemander(String demander) {
        return userDao.listDemander(demander);
    }

    @Override
    public List<User> listProvider(String provider) {
        return userDao.listProvider(provider);
    }

}
