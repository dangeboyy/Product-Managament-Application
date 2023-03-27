package exceptions;

public class CanNotAttachMaterialToPartException extends Exception {
    String message;
    public CanNotAttachMaterialToPartException(String message){
        this.message=message;
        System.out.println(message);
    }
    public String getMessage() {
        return this.message;
    }


}
