package ecom.clothes.service;

import ecom.clothes.controller.request.SignInRequest;
import ecom.clothes.controller.response.TokenResponse;

public interface AuthenticationService {
    TokenResponse getAccessToken(SignInRequest signInRequest);

    TokenResponse getRefreshToken(String req);
}
