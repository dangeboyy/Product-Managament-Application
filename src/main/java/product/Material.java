package product;

import lombok.Data;
import status.IStatus;
import status.NotStarted;
import users.Manager;

@Data
public abstract class Material {
    private String title;
    private String id;
    private IStatus status=new NotStarted();

    public Material(String title) {
        this.title = title;
        this.id=generateId();
    }

    public Material(String title, IStatus status) {
        this.title = title;
        this.status = status;
    }

    public Material() {
    }
    abstract String generateId();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
