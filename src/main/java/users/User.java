package users;

import lombok.Data;

@Data
public abstract class User {
    private String name;
    private String id;
    private String password;

    public User(String name) {
        this.name = name;
        this.id=generateId();
        this.password=generatePassword();
    }
    public User(){

    }
    abstract String generateId();
    abstract String generatePassword();
}
