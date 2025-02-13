package ecom.clothes.model;

import java.io.Serializable;
import jakarta.persistence.*;

@Embeddable
public class ModelHasRoleIdEntity implements Serializable {
    private Long roleId;
    private Long modelId;

    // Constructors, Getters, Setters, equals(), hashCode()
}
