package authentication.factory;

import authentication.guest.User;

public interface UserFactory {
    User createUser(String userName, String passwordHash);
}

