package users;

import lombok.Data;


@Data
public class Manager extends User{

    public Manager(String name) {
        super(name);
    }

    @Override
    String generateId() {
        return "M"+String.valueOf(data.Data.managers.size()+1);
    }
    @Override
    String generatePassword() {
        return getName()+generateId();
    }
}
