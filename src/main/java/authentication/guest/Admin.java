package authentication.guest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Admin implements User {
    private String userName;
    private String password;
    @JsonProperty("userType")
    private String userType;

    public Admin(@JsonProperty("userName") String userName,  @JsonProperty("password") String passwordHash) {
        this.userName = userName;
        this.password = passwordHash;
        this.userType = "ADMIN";
    }

    @Override
    public String getUserName() { return this.userName; }

    @Override
    public String getPassword() { return this.password; }

    @Override @JsonProperty("userType")
    public String getUserType() { return "ADMIN"; }

    @Override @JsonProperty("userType")
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public void displayInfo() {
        System.out.println("Username админа: " + userName);
    }
}
