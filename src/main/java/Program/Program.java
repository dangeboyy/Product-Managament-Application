package Program;

import data.Data;
import input.TakeInput;
import jsonIO.JsonRead;
import jsonIO.JsonWrite;
import menu.AdminMenu;
import menu.EmployeeMenu;
import menu.ManagerMenu;
import operations.AdminOperations;
import operations.EmployeeOperations;
import operations.ManagerOperations;
import product.Part;
import product.Product;
import users.Admin;
import users.Employee;
import users.Manager;
import users.User;

public class Program {
    LogIn logIn=new LogIn();
    AdminMenu adminMenu=new AdminMenu();
    AdminOperations adminOperations=new AdminOperations();
    ManagerOperations managerOperations=new ManagerOperations();
    EmployeeOperations employeeOperations=new EmployeeOperations();
    EmployeeMenu employeeMenu=new EmployeeMenu();
    ManagerMenu managerMenu=new ManagerMenu();
    TakeInput input=new TakeInput();
    JsonRead jsonRead=new JsonRead();

    public void program(){
        jsonRead.readProductFromJsonFile();
        jsonRead.readAssemblyJsonFile();
        jsonRead.readPartFromJsonFile();
        displayLoginInformation();

        while (true){
            System.out.println("Please enter Id or press 0 to EXIT:");
            String id=input.strInput();
            if(id.equals("0")){
                JsonWrite jsonIO=new JsonWrite();
                jsonIO.writeProductToJsonFile();
                jsonIO.writeAssemblyToJsonFile();
                jsonIO.writePartToJsonFile();
                Data.clearAll();
                break;
            }
            System.out.println("Please enter password:");
            String password=input.strInput();
            if(logIn.LogIn(Data.allUsers,id,password)){
                User user=findUserFromId(id);
                if(user instanceof Admin){
                    Admin admin=(Admin) user;
                    System.out.println("You logged in as Admin");
                    while (true) {
                        adminMenu.adminMenu();
                        System.out.println("Please select an operation you want to execute");
                        String selectAdminOption=input.strInput();
                        if(selectAdminOption.equals("0")){
                            adminOption0();
                            break;
                        }
                        else if(selectAdminOption.equals("1")){
                            adminOption1(admin);
                        }
                        else if(selectAdminOption.equals("2")){
                            adminOption2();
                        }
                        else if(selectAdminOption.equals("3")){
                            adminOption3();
                        }else if(selectAdminOption.equals("4")){
                            adminOption4();
                        }else{
                            System.out.println("Enter a valid input");
                        }
                    }
                }
                else if(user instanceof Manager){
                    Manager manager=(Manager) user;
                    Product relatedProduct=findProductFromRelatedManager(manager);
                    System.out.println("You logged in as Manager");
                    while(true){
                        managerMenu.managerMenu();
                        System.out.println("Please select an operation you want to execute");
                        String selectManagerOption=input.strInput();
                        if(selectManagerOption.equals("0")){
                            managerOption0();
                            break;
                        }
                        else if(selectManagerOption.equals("1")){
                            managerOption1(manager, relatedProduct);
                        }else if (selectManagerOption.equals("2")){
                            managerOption2(manager);
                        }else if(selectManagerOption.equals("3")){
                            managerOption3(relatedProduct);
                        }
                    }
                }
                else if(user instanceof Employee){
                    Employee employee=(Employee) user;
                    System.out.println("You logged in as Employee");
                    while (true){
                        employeeMenu.employeeMenu();
                        System.out.println("Please select an operation you want to execute");
                        String selectEmployeeOption=input.strInput();
                        if(selectEmployeeOption.equals("0")){
                            employeeOption0();
                            break;
                        }
                        else if(selectEmployeeOption.equals("1")){
                            Part part=findPartFromRelatedEmployee(employee);
                            if(part!=null){
                                employeeOption1(employee,part);
                            }
                        }
                    }
                }
            }
        }
    }
    //Admin Options//
    private void adminOption0(){
        System.out.println("You logged out");
    }
    private void adminOption1(Admin admin){
        Product product=adminOperations.createProduct(admin);
        System.out.println(product.getStatus().getClass().getSimpleName());
    }
    private void adminOption2(){
        adminOperations.seeManagers();

    }
    private void adminOption3(){

        adminOperations.seeEmployess();
    }
    private void adminOption4(){
        adminOperations.seeProductTree();
    }
    //Manager Options//
    private void managerOption0(){
        System.out.println("You logged out");
    }
    private void managerOption1(Manager manager,Product product){
        if (product.getChildren().isEmpty()) {
            managerOperations.ManOp(manager, product);
        }else{
            System.out.println("You can not add more material because you already build this product");
        }
    }
    private Product findProductFromRelatedManager(Manager manager){
        Product product=null;
        for (Product p:Data.products
             ) {
            if(p.getManager().getName().equals(manager.getName())){
                product=p;

            }

        }
        return product;
    }
    private void managerOption2(Manager manager) {
        managerOperations.seeRelatedEmployees(manager);
    }
    private void managerOption3(Product product){
        managerOperations.seeProductTree(product);

    }
    //Employee Options//
    private void employeeOption0(){
        System.out.println("You logged out");
    }
    private void employeeOption1(Employee employee,Part part){
        for (Part p:Data.parts
             ) {
            if(p.getRelatedEmployee().getName().equals(part.getRelatedEmployee().getName())){
                System.out.println("Your related part is: "+p.getTitle());
                System.out.println("Part status is: "+p.getStatus().getClass().getSimpleName());
                employeeOperations.changeMaterialStatus(employee,p);
            }
        }

    }
    private Part findPartFromRelatedEmployee(Employee employee){
        Part part=null;
        for (Part p:Data.parts
             ) {
            if(p.getRelatedEmployee().getName().equals(employee.getName())){
                part=p;
            }
        }
        return part;
    }
    private User findUserFromId(String id){
        User user=null;
        for (User u:Data.allUsers) {
            if(u.getId().equals(id)){
                user=u;
            }
        }
        return user;
    }
    public void displayLoginInformation(){
        for (User user:Data.allUsers
             ) {
            System.out.println("Login Id:"+user.getId()+"Login password:"+user.getPassword());

        }
    }

}
