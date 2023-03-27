package data;

import product.Assembly;
import product.Part;
import product.Product;
import users.Employee;
import users.Manager;
import users.User;

import java.util.ArrayList;

public class Data {
    public static ArrayList<User> allUsers=new ArrayList<>();
    public static ArrayList<Manager> managers=new ArrayList<>();
    public static ArrayList<Employee> employees=new ArrayList<>();
    public static ArrayList<Product> products=new ArrayList<>();
    public static ArrayList<Part> parts=new ArrayList<>();
    public static ArrayList<Assembly> assemblies=new ArrayList<>();



    public static void clearAll(){
        allUsers.clear();
        managers.clear();
        employees.clear();
        products.clear();
        parts.clear();
        assemblies.clear();
    }



}
