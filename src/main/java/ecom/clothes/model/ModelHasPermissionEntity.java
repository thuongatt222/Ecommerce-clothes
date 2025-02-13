package ecom.clothes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_model_has_permissions")
public class ModelHasPermissionEntity extends Timestamp{
    @EmbeddedId
    private ModelHasPermissionIdEntity id;

    // Constructors, Getters, Setters
}
