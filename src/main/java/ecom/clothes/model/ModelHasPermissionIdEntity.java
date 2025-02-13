package ecom.clothes.model;

import java.io.Serializable;
import jakarta.persistence.*;

@Embeddable
public class ModelHasPermissionIdEntity implements Serializable {
    private Long modelId;
    private Long permissionId;

    // Constructors, Getters, Setters, equals(), hashCode()
}
