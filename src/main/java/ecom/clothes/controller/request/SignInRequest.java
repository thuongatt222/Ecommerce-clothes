package ecom.clothes.controller.request;

import lombok.Getter;

@Getter
public class SignInRequest {
    private String username;
    private String password;
    private String deviceToken;
    private String version;
    private String platform;
}
