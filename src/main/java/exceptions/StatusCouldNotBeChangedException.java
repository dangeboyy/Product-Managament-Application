package exceptions;

public class StatusCouldNotBeChangedException extends Exception{
    String message;
    public StatusCouldNotBeChangedException(String message){
        this.message=message;
        System.out.println(message);
    }
    public String getMessage() {
        return this.message;
    }

}
