package com.flow.backend.config;

import com.flow.backend.AuthoritiesConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    AuthoritiesConverter realmRolesAuthoritiesConverter() {
        return claims -> {
            var realmAccess = Optional.ofNullable((Map<String, Object>) claims.get("realm_access"));
            var roles = realmAccess.flatMap(map -> Optional.ofNullable((List<String>) map.get("roles")));
            return roles.stream().flatMap(Collection::stream)
                    .map(SimpleGrantedAuthority::new)
                    .map(GrantedAuthority.class::cast)
                    .toList();
        };
    }

    @Bean
    JwtAuthenticationConverter authenticationConverter(AuthoritiesConverter authoritiesConverter){
        var authorizationConverter = new JwtAuthenticationConverter();
        authorizationConverter.setJwtGrantedAuthoritiesConverter(
                jwt -> authoritiesConverter.convert(jwt.getClaims())
        );
        return authorizationConverter;
    }

    @Bean
    SecurityFilterChain resourceServerSecurityFilterChain(HttpSecurity http, Converter<Jwt, AbstractAuthenticationToken> authenticationConverter) {
        http.oauth2ResourceServer(
                resourceServer -> resourceServer.jwt(
                        jwtDecoder -> jwtDecoder.jwtAuthenticationConverter(authenticationConverter)
                )
        );

        http.sessionManagement(
                sessions -> sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).csrf(AbstractHttpConfigurer::disable
        );

        http.authorizeHttpRequests(requests -> {
            requests.requestMatchers(
                    "/hello",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/actuator/**"
            ).permitAll();
            requests.requestMatchers("/api/**").authenticated();
            requests.anyRequest().denyAll();
        });

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}