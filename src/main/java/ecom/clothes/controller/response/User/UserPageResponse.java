package ecom.clothes.controller.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPageResponse extends GlobalResponse{
    private List<UserResponse> users;
}
