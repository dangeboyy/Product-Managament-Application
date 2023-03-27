package status;

import product.Material;

public interface IStatus {
    public void logMessage(Material material);
    public void changeStatus(Material material);
}
