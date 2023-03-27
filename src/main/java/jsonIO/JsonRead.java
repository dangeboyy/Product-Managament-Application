package jsonIO;

import data.Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import product.Assembly;
import product.Material;
import product.Part;
import product.Product;
import status.Complete;
import status.IStatus;
import status.InProgress;
import status.NotStarted;
import users.Admin;

import users.Manager;
import users.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonRead {
    public void readProductFromJsonFile(){
        Data.allUsers.add(new Admin("Admin"));
        JSONParser jsonParser=new JSONParser();
        try {
            FileReader reader=new FileReader("product.json");
            Object obj=jsonParser.parse(reader);
            JSONArray productList=(JSONArray) obj;
            productList.forEach(prd->parseProductObject((JSONObject) prd));

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.getMessage();
        } catch (ParseException parseException) {
            parseException.getMessage();
        } catch (IOException ioException) {
            ioException.getMessage();
        }

    }
    private void parseProductObject(JSONObject productt){
        JSONObject productObject=(JSONObject) productt.get("Product");
        String title=(String) productObject.get("Product title");
        String managerName= (String) productObject.get("Manager Name");
        Product product=new Product(title,(Admin) Data.allUsers.get(0),managerName);
        Data.products.add(product);
    }
    public void readPartFromJsonFile(){
        JSONParser jsonParser=new JSONParser();

        try (FileReader reader = new FileReader("part.json")) {
            Object obj=jsonParser.parse(reader);
            JSONArray partList=(JSONArray)  obj;
            partList.forEach(prt->parsePartObject((JSONObject) prt));
         } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } catch (ParseException e) {
            e.getMessage();
        }

    }
    private void parsePartObject(JSONObject part){
        JSONObject partObject=(JSONObject) part.get("Part");
        String title=(String) partObject.get("Part title");
        String manName=(String) partObject.get("Manager Name");
        String parentName=(String) partObject.get("Parent Name");
        String statusName=(String) partObject.get("Status Detail");
        String employeeName=(String) partObject.get("Employee Name");
        Manager manager=(Manager) getuser(manName);
        Material parent=getParent(parentName);
        IStatus status=getStatus(statusName);
        Part partt=new Part(title,status,employeeName,manager,parent);
        Data.parts.add(partt);
        addChildren(parent,partt);
    }

    public void readAssemblyJsonFile(){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("assembly.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray assemblyList = (JSONArray) obj;
            assemblyList.forEach( ass -> parseAssemblyObject( (JSONObject) ass ));
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } catch (ParseException e) {
            e.getMessage();
        }
    }

    private void parseAssemblyObject(JSONObject ass){
        JSONObject assObject = (JSONObject) ass.get("Assembly");
        String title = (String) assObject.get("Assembly title");
        String manName= (String) assObject.get("Manager Name");
        Manager manager = (Manager) getuser(manName);
        String parentName = (String) assObject.get("Parent Name");
        Material parent = getParent(parentName);
        String statusName=(String) assObject.get("Status Detail");
        IStatus status = getStatus(statusName);
        Assembly assembly = new Assembly(title, manager, parent, status);
        Data.assemblies.add(assembly);
        addChildren(parent,assembly);
    }


    private Material getParent(String parentName){
        for (Material m:Data.products) {
            if (m.getTitle().equals(parentName)){
                return m;
            }
        }
        for (Material m:Data.assemblies) {
            if (m.getTitle().equals(parentName)){
                return m;
            }
        }
        System.out.println("Parent bulunamadı");
        return null;
    }
    private IStatus getStatus(String statusName){
        if (statusName.equals("NotStarted")){
            return new NotStarted();
        }else if (statusName.equals("InProgress")){
            return new InProgress();
        }else if (statusName.equals("Complete")){
            return new Complete();
        }else{
            System.out.println("Invalid status");
            return null;
        }
    }
    private User getuser(String userName){
        for (Product pr:Data.products) {
            if (pr.getManager().getName().equals(userName)){
                return pr.getManager();
            }
        }return null;
    }
    private void addChildren(Material material,Material child){
        if (material instanceof Product){
            Product prod = (Product) material;
            prod.getChildren().add(child);
        }else if (material instanceof Assembly){
            Assembly assembly = (Assembly) material;
            assembly.getChildren().add(child);
        }else {
            System.out.println("Part Does Not Have Material");
        }
    }

}
