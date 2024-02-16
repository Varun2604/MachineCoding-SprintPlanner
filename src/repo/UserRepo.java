package repo;

import models.User;

public interface UserRepo {

    User createUser(User user);

    User getUser(int id);
}
