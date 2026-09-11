package com.flow.backend.controllers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
public class MeController {
    @GetMapping("/me")
    public UserInfoDto getGetting(JwtAuthenticationToken auth) {
        return new UserInfoDto(
                auth.getToken().getClaimAsString(StandardClaimNames.PREFERRED_USERNAME),

                auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
        );
    }

    public record UserInfoDto(String name, List roles) { }
}
