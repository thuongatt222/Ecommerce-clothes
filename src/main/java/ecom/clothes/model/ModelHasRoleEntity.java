package ecom.clothes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_model_has_roles")
public class ModelHasRoleEntity {
    @EmbeddedId
    private ModelHasRoleIdEntity id;
}
