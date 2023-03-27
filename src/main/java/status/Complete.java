package status;

import product.Material;

public class Complete implements IStatus{

    @Override
    public void logMessage(Material material) {
        System.out.println(material.getTitle()+" status is already Complete ");

    }


    @Override
    public void changeStatus(Material material) {
        logMessage(material);
    }

}
