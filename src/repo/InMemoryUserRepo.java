package repo;

import models.User;

import java.util.HashMap;

public class InMemoryUserRepo implements UserRepo {

    private final HashMap<Integer, User> userById = new HashMap<>();

    @Override
    public User createUser(User user) {
        return userById.put(user.getId(), user);
    }

    @Override
    public User getUser(int id) {
        return userById.get(id);
    }
}
