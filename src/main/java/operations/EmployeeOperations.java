package operations;

import exceptions.UnauthorizedUserOperationException;
import product.Assembly;
import product.Material;
import product.Part;
import users.Employee;
import users.User;

public class EmployeeOperations {
    public void changeMaterialStatus(User user, Material material) {
        if (user instanceof Employee) {
            material.getStatus().changeStatus(material);
            changeStatusAutomatically(material);
            System.out.println("All status changed automatically");
        } else {
            try {
                throw new UnauthorizedUserOperationException("You are not authorized to change status!");
            } catch (UnauthorizedUserOperationException e) {
                e.getMessage();
            }
        }
    }

    private void changeStatusAutomatically(Material material) {
        if (material instanceof Part) {
            Part part = (Part) material;
            part.getParent().getStatus().changeStatus(part.getParent());
            changeStatusAutomatically(part.getParent());
        } else if (material instanceof Assembly) {
            Assembly assembly = (Assembly) material;
            assembly.getParent().getStatus().changeStatus(assembly.getParent());
            changeStatusAutomatically(assembly.getParent());
        }
    }
}
