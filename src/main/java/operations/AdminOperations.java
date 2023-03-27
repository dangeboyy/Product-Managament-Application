package operations;

import data.Data;
import exceptions.ProductNotCreatedException;
import input.TakeInput;
import product.Part;
import product.Product;
import users.Admin;
import users.Employee;
import users.Manager;
import users.User;

public class AdminOperations {
    public Product createProduct(Admin admin){//
        TakeInput takeInput=new TakeInput();
        System.out.println("Please enter product title you want to create:");
        String title=takeInput.strInput();
        System.out.println("Enter the name of the manager to be assigned to the product:");
        String managerName= takeInput.strInput();
        Product product=new Product(title,admin,managerName);
        System.out.println(product.getTitle()+" is created");
        Data.products.add(product);
        return product;
    }
    public void seeManagers(){
        System.out.println("Manager List:");
        helperSeeManager();
    }
        private void helperSeeManager(){
            if (!Data.managers.isEmpty()) {
                for (Manager m : Data.managers
                ) {
                    System.out.println("Manager name:" + m.getName() + "\n" + "Manager id:" + m.getId());
                }
            }else{
                try {
                    throw new ProductNotCreatedException("There is no manager because no product is created");
                } catch (ProductNotCreatedException e) {
                    e.getMessage();
                }
            }

        }
        public void seeEmployess(){
            System.out.println("Employee List:");
            helperSeeEmployess();

        }
        private void helperSeeEmployess(){
            for (Employee e:Data.employees) {
               for(int i=0;i<Data.parts.size();i++){
                   if(Data.parts.get(i).getRelatedEmployee().getName().equals(e.getName())) {
                       System.out.println("Employee name: " + e.getName() + " Employee id: " + e.getId() + " Related part: " + Data.parts.get(i).getTitle());
                   }
               }
            }
        }
        public void seeProductTree(){
            System.out.println("Product Tree:");
            helperSeeProductTree();

        }
        private void helperSeeProductTree(){
            if (!Data.products.isEmpty()){
                for (Product pr:Data.products) {
                    pr.toStringProduct();
                }
            }else{
                try {
                    throw new ProductNotCreatedException("There is no product tree because no product is created");
                } catch (ProductNotCreatedException e) {
                    e.getMessage();
                }
            }
        }


}
