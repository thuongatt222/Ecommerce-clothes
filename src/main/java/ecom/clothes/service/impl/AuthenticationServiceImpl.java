package ecom.clothes.service.impl;

import ecom.clothes.common.TokenType;
import ecom.clothes.controller.request.SignInRequest;
import ecom.clothes.controller.response.TokenResponse;
import ecom.clothes.exception.ForBiddenException;
import ecom.clothes.model.UserEntity;
import ecom.clothes.repositories.UserRepository;
import ecom.clothes.service.AuthenticationService;
import ecom.clothes.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "Authentication-Service")
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public TokenResponse getAccessToken(SignInRequest signInRequest) {

        List<String> authorities = new ArrayList<>();

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
            authorities.add(authentication.getAuthorities().toString());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (AuthenticationException e){
            throw new AccessDeniedException(e.getMessage());
        }

        String accessKey = jwtService.generateAccessToken(signInRequest.getUsername(), authorities);
        String refreshKey = jwtService.generateRefreshToken(signInRequest.getUsername(), authorities);

        return TokenResponse.builder().accessToken(accessKey).refreshToken(refreshKey).build();
    }

    @Override
    public TokenResponse getRefreshToken(String req) {
        try {
            String username = jwtService.extractUsername(req, TokenType.REFRESH_TOKEN);

            UserEntity user = userRepository.findByUsername(username);
            List<String> authorities = new ArrayList<>();
            user.getAuthorities().forEach(authority -> authorities.add(authority.getAuthority()));

            String accessKey = jwtService.generateAccessToken(username, authorities);

            return TokenResponse.builder().accessToken(accessKey).refreshToken(req).build();
        }catch (Exception e){
            throw new ForBiddenException(e.getMessage());
        }
    }
}
