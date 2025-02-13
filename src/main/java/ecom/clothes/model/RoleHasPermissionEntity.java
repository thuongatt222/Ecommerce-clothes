package ecom.clothes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_role_has_permission")
public class RoleHasPermissionEntity extends Timestamp{
    @EmbeddedId
    private RoleHasPermissionIdEntity id;
}
