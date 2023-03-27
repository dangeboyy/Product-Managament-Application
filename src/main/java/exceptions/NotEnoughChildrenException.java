package exceptions;

public class NotEnoughChildrenException extends Exception{
    String message;
    public NotEnoughChildrenException(String message){
        this.message=message;
        System.out.println(message);
    }
    public String getMessage() {
        return this.message;
    }

}
