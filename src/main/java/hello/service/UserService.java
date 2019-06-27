package hello.service;

import hello.model.User;

import java.util.List;

public interface UserService {
    void insert(User user);
    void insertAll(List<User> users);
    List<User> loadAllUser();
    User getUserByLogin(String login);
}
