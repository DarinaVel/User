package hello.service;

import hello.model.User;

import java.util.List;

public interface UserService {
    void insert(User user);
    void insertAll(List<User> users);
    void loadAllUser();
    void getUserByLogin(String login);
}
