package jsonIO;
import data.Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import product.Assembly;
import product.Part;
import product.Product;
import java.io.FileWriter;
import java.io.IOException;



public class JsonWrite {

    public void writeProductToJsonFile(){
        JSONArray productList=new JSONArray();
        for (Product p: Data.products) {
            JSONObject productDetails=productDetails(p);
            JSONObject product=new JSONObject();
            product.put("Product",productDetails);
            productList.add(product);

            try(FileWriter fileWriter=new FileWriter("product.json")){
                fileWriter.write(productList.toJSONString());
                fileWriter.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void writeAssemblyToJsonFile(){
        JSONArray assemblyList=new JSONArray();
        for (Assembly ass: Data.assemblies) {
            JSONObject assemblyDetails=assemblyDetails(ass);
            JSONObject assembly=new JSONObject();
            assembly.put("Assembly",assemblyDetails);
            assemblyList.add(assembly);

            try(FileWriter fileWriter=new FileWriter("assembly.json")){
                fileWriter.write(assemblyList.toJSONString());
                fileWriter.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void writePartToJsonFile(){
        JSONArray partList=new JSONArray();
        for (Part p:Data.parts
             ) {
            JSONObject partDetails=partDetails(p);
            JSONObject part=new JSONObject();
            part.put("Part",partDetails);
            partList.add(part);

            try(FileWriter fileWriter=new FileWriter("part.json")){
                fileWriter.write(partList.toJSONString());
                fileWriter.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private JSONObject partDetails(Part part){
        JSONObject partDetails=new JSONObject();
        partDetails.put("Part title",part.getTitle());
        partDetails.put("Manager Name",part.getRelatedManager().getName());
        partDetails.put("Parent Name",part.getParent().getTitle());
        partDetails.put("Status Detail",part.getStatus().getClass().getSimpleName());
        partDetails.put("Employee Name",part.getRelatedEmployee().getName());
        return partDetails;

    }

    private JSONObject productDetails(Product product){
        JSONObject productDetails=new JSONObject();
        productDetails.put("Product title",product.getTitle());
        productDetails.put("Manager Name",product.getManager().getName());
        productDetails.put("Status Detail",product.getStatus().getClass().getSimpleName());
        return productDetails;
    }

    private JSONObject assemblyDetails(Assembly assembly){
        JSONObject assemblyDetails=new JSONObject();
        assemblyDetails.put("Assembly title",assembly.getTitle());
        assemblyDetails.put("Manager Name",assembly.getRelatedManager().getName());
        assemblyDetails.put("Parent Name",assembly.getParent().getTitle());
        assemblyDetails.put("Status Detail",assembly.getStatus().getClass().getSimpleName());
        return assemblyDetails;
    }



}
