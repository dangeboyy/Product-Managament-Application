package product;

import lombok.Data;
import users.Admin;
import users.Manager;

import java.util.ArrayList;

@Data
public class Product extends Material {
   private Admin createdAdmin;
   private Manager manager;
   private ArrayList<Material> children = new ArrayList<>();

   public Product(String title, Admin createdAdmin,String manName) {
      super(title);
      this.createdAdmin = createdAdmin;
      this.manager = new Manager(manName);
      data.Data.managers.add(manager);
      data.Data.allUsers.add(manager);
   }

   public Product() {
      super();
   }

   @Override
   String generateId() {
      return "Pr"+ String.valueOf(data.Data.products.size()+1);
   }
   public void toStringProduct(){
      System.out.println("Product Name: "+ this.getTitle());
      for(int i=0;i<children.size();i++) {
         if(children.get(i) instanceof Part){
            Part part=(Part) children.get(i);
            if(i!=children.size()-1){
               System.out.print(i+1 + ". material "+part.toStringPart()+" ");
            }
            else{
               System.out.println(i+1 + ". material "+part.toStringPart());
            }

         }
         else if(children.get(i) instanceof Assembly){
            Assembly assembly=(Assembly) children.get(i);
            System.out.println(i+1 + ". material "+assembly.getTitle()+"("+assembly.getClass().getSimpleName()+")");
            System.out.println(assembly.getTitle()+" materials:");
            assembly.toStringAssembly();
         }

      }
   }
   public boolean checkNumberOfChildren(){
      return children.size() >= 2;
   }
}
