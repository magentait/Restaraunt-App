package authentication.guest;

public interface User {
    String getUserName();
    String getPassword();
    String getUserType();
    void setUserType(String userType);
    void displayInfo();
}

