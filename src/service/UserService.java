package service;

import models.User;

public interface UserService {
    User createUser(User user);

    User getUser(int id);
}
