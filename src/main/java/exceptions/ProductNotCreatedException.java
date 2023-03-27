package exceptions;

public class ProductNotCreatedException extends Exception {
    String message;
    public ProductNotCreatedException(String message){
        this.message=message;
        System.out.println(message);
    }
    public String getMessage() {
        return this.message;
    }
}
