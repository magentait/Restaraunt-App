package authentication.guest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Visitor implements User {
    private String userName;
    private String password;
    private String userType;

    public Visitor(@JsonProperty("userName") String userName,  @JsonProperty("password") String passwordHash) {
        this.userName = userName;
        this.password = passwordHash;
        userType = "VISITOR";
    }

    @Override
    public String getUserName() { return this.userName; }
    @Override
    public String getPassword() { return this.password; }

    @Override @JsonProperty("userType")
    public String getUserType() { return "VISITOR"; }

    @Override @JsonProperty("userType")
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public void displayInfo() {
        System.out.println("Username клиента: " + userName);
    }
}

