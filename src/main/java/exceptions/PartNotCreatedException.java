package exceptions;










public class PartNotCreatedException extends Exception {
    String message;
    public PartNotCreatedException(String message) {
        this.message=message;
    }
    public String getMessage() {
        return this.message;
    }
}
