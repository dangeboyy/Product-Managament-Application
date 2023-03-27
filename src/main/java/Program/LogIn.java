package Program;

import users.User;

import java.util.ArrayList;

public class LogIn {
    public boolean LogIn(ArrayList<User> user, String id, String password) {
        boolean signInControl = false;
        for (User value : user) {
            if (id.equals(value.getId())) {
                if (password.equals(value.getPassword())) {
                    System.out.println("Welcome " + value.getName());
                    signInControl = true;
                    return signInControl;
                }
            }
        }
        System.out.println("Email or password is wrong!!");
        return signInControl;
    }
}
