package hello.service.impl;

import hello.dao.UserDao;
import hello.model.User;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public void insertAll(List<User> users) {
        userDao.insertAll(users);
    }

    @Override
    public void loadAllUser() {
        List<User> listUser = userDao.loadAllUser();
        for(User user: listUser){
            System.out.println(user.toString());
        }
    }

    @Override
    public void getUserByLogin(String login) {
        User user = userDao.findUserByLogin(login);
        System.out.println(user.toString());
    }
}
