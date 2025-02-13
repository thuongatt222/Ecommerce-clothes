package ecom.clothes.model;

import java.io.Serializable;
import jakarta.persistence.*;

@Embeddable
public class RoleHasPermissionIdEntity implements Serializable {
    private Long roleId;
    private Long permissionId;

    // Constructors, Getters, Setters, equals(), hashCode()
}
