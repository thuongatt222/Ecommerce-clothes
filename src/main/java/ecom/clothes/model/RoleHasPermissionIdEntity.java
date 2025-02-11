package ecom.clothes.model;

import java.io.Serializable;
import jakarta.persistence.*;

@Embeddable
public class RoleHasPermissionIdEntity implements Serializable {
    private int roleId;
    private int permissionId;

    // Constructors, Getters, Setters, equals(), hashCode()
}
