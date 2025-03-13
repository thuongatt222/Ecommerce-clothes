package ecom.clothes.controller.response.User;

import ecom.clothes.controller.response.GlobalResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPageResponse extends GlobalResponse {
    private List<UserResponse> users;
}
