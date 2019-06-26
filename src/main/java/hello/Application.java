package hello;
import hello.model.User;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@EnableAutoConfiguration
@ComponentScan("hello.service.impl, hello.dao.impl")
public class Application {

    @Autowired
    UserService userService;
    public static void main(String[] args)
    {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        UserService userService = context.getBean(UserService.class);

        User user1 = new User();
        user1.setLogin("user1");
        user1.setName("User 1");
        user1.setPassword("qwerty123");
        user1.setBirthdate(new Date("23.04.1994"));

        User user2 = new User();
        user2.setLogin("user2");
        user2.setName("User 2");
        user2.setPassword("123456789");
        user2.setBirthdate(new Date("12.12.2012"));

        User user3 = new User();
        user3.setLogin("user3");
        user3.setName("User 3");
        user3.setPassword("113333");
        user3.setBirthdate(new Date("15.11.1974"));

        userService.insert(user1);

        List<User> users = new ArrayList<User>();
        users.add(user2);
        users.add(user3);
        userService.insertAll(users);


        userService.loadAllUser();

        userService.getUserByLogin("user1");
    }
}
