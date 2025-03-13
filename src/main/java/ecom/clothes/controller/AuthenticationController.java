package ecom.clothes.controller;

import ecom.clothes.controller.request.SignInRequest;
import ecom.clothes.controller.response.TokenResponse;
import ecom.clothes.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Authentication Controller")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Access Token")
    @PostMapping("/access-token")
    public TokenResponse accessToken(@RequestBody SignInRequest signInRequest) {
        return authenticationService.getAccessToken(signInRequest);
    }

    @Operation(summary = "Refresh Token")
    @PostMapping("/refresh-token")
    public TokenResponse refreshToken(@RequestBody String refreshToken) {
        return TokenResponse.builder().accessToken("AN").refreshToken("RO").build();
    }
}
