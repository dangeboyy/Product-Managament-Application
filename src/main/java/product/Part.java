package product;


import data.Data;
import status.IStatus;
import users.Employee;
import users.Manager;

@lombok.Data
public class Part extends Material {
    private Employee relatedEmployee;
    private Manager relatedManager;
    private Material parent;

    public Part(String title, Manager relatedManager, Material parent,String empName) {
        super(title);
        this.relatedEmployee = new Employee(empName);
        this.relatedManager = relatedManager;
        this.parent = parent;
        Data.employees.add(relatedEmployee);
        Data.allUsers.add(relatedEmployee);
    }

    public Part(String title, IStatus status, String empName, Manager relatedManager, Material parent) {
        super(title, status);
        this.relatedEmployee = new Employee(empName);
        this.relatedManager = relatedManager;
        this.parent = parent;
        Data.employees.add(relatedEmployee);
        Data.allUsers.add(relatedEmployee);
    }

    @Override
    String generateId() {
        return "PA"+ String.valueOf(Data.parts.size());
    }
    public String toStringPart(){
        return relatedEmployee.getName() + "("+this.getTitle()+")"+"("+this.getClass().getSimpleName()+")";
    }


}
