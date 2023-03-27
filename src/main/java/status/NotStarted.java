package status;

import product.Material;

public class NotStarted implements IStatus{
    @Override
    public void logMessage(Material material) {
        System.out.println(material.getTitle()+" status is changed to In Progress");
    }

    @Override
    public void changeStatus(Material material) {
        material.setStatus(new InProgress());
        logMessage(material);


    }
}
