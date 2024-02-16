package service;

import models.User;
import repo.UserRepo;

public class UserServiceImpl implements UserService{

    UserRepo repo;
    public UserServiceImpl(UserRepo repo){
        this.repo = repo;
    }
    @Override
    public User createUser(User user) {
        return this.repo.createUser(user);
    }

    @Override
    public User getUser(int id) {
        return this.repo.getUser(id);
    }
}
