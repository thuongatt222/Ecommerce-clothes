package ecom.clothes.model;

import java.io.Serializable;
import jakarta.persistence.*;

@Embeddable
public class ModelHasRoleIdEntity implements Serializable {
    private int roleId;
    private int modelId;

    // Constructors, Getters, Setters, equals(), hashCode()
}
