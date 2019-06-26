package hello.dao;

import hello.model.User;

import java.util.List;

public interface UserDao {
    void insert(User user);
    void insertAll(List<User> users);
    List<User> loadAllUser();
    User findUserByLogin(String login);
}
