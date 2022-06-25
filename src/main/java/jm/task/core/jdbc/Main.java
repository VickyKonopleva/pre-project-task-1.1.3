package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        UserService us = new UserServiceImpl();
        User user1 = new User("Victora", "Konopleva", (byte) 24);
        User user2 = new User("Ross", "Korol", (byte) 24);
        User user3 = new User("John", "Snow", (byte) 29);
        User user4 = new User("Jimmy", "Choo", (byte) 49);
        ArrayList<User> users= new ArrayList<>(List.of(user1, user2, user3, user4));
        us.createUsersTable();
        for (User user: users) {
            us.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User с именем " +  user.getName() + " добавлен в базу данных");
        }


        List<User> usersBD = us.getAllUsers();
        for (User user: usersBD) {
            System.out.println(user);
        }
        us.cleanUsersTable();
        us.dropUsersTable();
    }
}
