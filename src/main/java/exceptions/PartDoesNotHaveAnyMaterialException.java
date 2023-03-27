package exceptions;

public class PartDoesNotHaveAnyMaterialException extends Exception{
    String message;
    public PartDoesNotHaveAnyMaterialException(String message){
        this.message=message;
        System.out.println(message);
    }
    public String getMessage() {
        return this.message;
    }

}
