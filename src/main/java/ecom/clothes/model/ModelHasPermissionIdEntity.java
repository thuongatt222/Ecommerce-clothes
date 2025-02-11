package ecom.clothes.model;

import java.io.Serializable;
import jakarta.persistence.*;

@Embeddable
public class ModelHasPermissionIdEntity implements Serializable {
    private int modelId;
    private int permissionId;

    // Constructors, Getters, Setters, equals(), hashCode()
}
