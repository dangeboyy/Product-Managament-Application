package status;

import exceptions.StatusCouldNotBeChangedException;
import product.Assembly;
import product.Material;
import product.Product;

public class InProgress implements IStatus{
    @Override
    public void logMessage(Material material) {
        System.out.println(material.getTitle()+" status has changed as Complete");
    }

    @Override
    public void changeStatus(Material material) {
        if (checkChildrenStatus(material)) {
            material.setStatus(new Complete());
            logMessage(material);
        }else{
            try {
                throw new StatusCouldNotBeChangedException(material.getTitle()+"'s Status could not be changed because all "+ material.getTitle() +" materials must be complete");
            } catch (StatusCouldNotBeChangedException e) {
                e.getMessage();
            }
        }
    }
    private boolean checkChildrenStatus(Material material) {
        boolean canBeComplete = true;
        if (material instanceof Product) {
            Product product = (Product) material;
            for (Material mat : product.getChildren()) {
                if (!(mat.getStatus() instanceof Complete)) { //if any child is not complete then returns false
                    canBeComplete = false;
                }
            }
        } else if (material instanceof Assembly) {
            Assembly assembly = (Assembly) material;
            for (Material mat : assembly.getChildren()) {
                if (!(mat.getStatus() instanceof Complete)) {
                    canBeComplete = false;
                }
            }
        }
        return canBeComplete;
    }
}

