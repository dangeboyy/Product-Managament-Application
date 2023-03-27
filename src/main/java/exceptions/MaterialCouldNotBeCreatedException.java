package exceptions;

public class MaterialCouldNotBeCreatedException extends Exception{
    String message;
    public MaterialCouldNotBeCreatedException(String message){
        this.message=message;
        System.out.println(message);
    }
    public String getMessage() {
        return this.message;
    }

}
