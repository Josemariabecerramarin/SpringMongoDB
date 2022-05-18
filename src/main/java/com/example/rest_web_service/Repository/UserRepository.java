package com.example.rest_web_service.Repository;

import com.example.rest_web_service.Model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepository {
    ArrayList<User> users = new ArrayList<>();

    public UserRepository() {
        /*users.add(new User(1, "user1@user.user", "user", "Usuario uno"));
        users.add(new User(2, "user2@user.user", "user", "Usuario dos"));
        users.add(new User(3, "user3@user.user", "user", "Usuario tres"));*/

    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public void saveUser(User user) {
        users.add(user);
    }

    public void deleteUser(int id) {
        users.removeIf(user -> user.getId() == id);
    }

    public void updateUser(User user, int id) {
        int i = 0;
        for (User us : users) {
            if (us.getId() == id) {
                break;
            }
            i++;
        }
        users.set(i, user);
    }
}
