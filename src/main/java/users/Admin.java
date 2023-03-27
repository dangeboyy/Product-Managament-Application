package users;


import lombok.Data;



@Data
public class Admin extends User {
    public Admin(String name) {
        super(name);
    }

    @Override
    String generateId() {
        return "A"+ String.valueOf(data.Data.allUsers.size());
    }
    @Override
    String generatePassword() {
        return getName()+generateId();
    }

}
