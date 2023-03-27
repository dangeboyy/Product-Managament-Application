package product;


import lombok.Data;
import lombok.EqualsAndHashCode;
import status.IStatus;
import users.Manager;
import java.util.ArrayList;


@EqualsAndHashCode(callSuper = true)
@Data
public class Assembly extends Material {
   private Manager relatedManager;
   private Material parent;
   private ArrayList<Material> children = new ArrayList<>();



   public Assembly(String title, Manager relatedManager, Material parent) {
      super(title);
      this.relatedManager = relatedManager;
      this.parent = parent;
   }
   public Assembly(String title, Manager relatedManager,Material parent,IStatus status) {
      super(title,status);
      this.relatedManager = relatedManager;
      this.parent = parent;
   }

   @Override
   String generateId() {
      return "AS"+String.valueOf(data.Data.assemblies.size());
   }
   public void toStringAssembly(){
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
