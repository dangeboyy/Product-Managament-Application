package users;


import lombok.Data;
@Data
public class Employee extends User{
    public Employee(String name) {
        super(name);

    }

    @Override
    String generateId() {
        return "E"+String.valueOf(data.Data.employees.size()+1);
    }

    @Override
    String generatePassword() {
        return getName()+generateId();
    }
}
