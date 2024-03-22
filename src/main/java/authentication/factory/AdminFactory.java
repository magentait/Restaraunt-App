package authentication.factory;

import authentication.guest.Admin;
import authentication.guest.User;

public class AdminFactory implements UserFactory {
    @Override
    public User createUser(String userName, String passwordHash) {
        return new Admin(userName, passwordHash);
    }
}
