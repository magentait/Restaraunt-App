package authentication.factory;

import authentication.guest.User;
import authentication.guest.Visitor;

public class VisitorFactory implements UserFactory {
    @Override
    public User createUser(String userName, String passwordHash) {
        return new Visitor(userName, passwordHash);
    }
}