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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.*;

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
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(List.of(
                "http://localhost:5173",
                "localhost:5173"
        ));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

    @Bean
    JwtAuthenticationConverter authenticationConverter(){
        JwtAuthenticationConverter authorizationConverter = new JwtAuthenticationConverter();
        authorizationConverter.setJwtGrantedAuthoritiesConverter(
            jwt -> {
                Collection<GrantedAuthority> authorities = new ArrayList<>();

                extractRolesFromClaim(jwt, "realm_access", authorities);
                extractRolesFromClaim(jwt, "resource_access", authorities, "codeflow");

                return authorities;
            }
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
                    "/api/*",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/actuator/**"
            ).permitAll();
            requests.requestMatchers("/**").authenticated();
            requests.anyRequest().denyAll();
        });

        http.csrf(AbstractHttpConfigurer::disable);

        http.cors(cors -> {
            cors.configurationSource(corsConfigurationSource());
        });

        http.formLogin(AbstractHttpConfigurer::disable);

        http.httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }

    private void extractRolesFromClaim(Jwt jwt, String claimName, Collection<GrantedAuthority> authorities){
        extractRolesFromClaim(jwt, claimName, authorities, null);
    }

    private void extractRolesFromClaim(Jwt jwt, String claimName, Collection<GrantedAuthority> authorities, String resource) {
        Map<String, Object> claim = jwt.getClaim(claimName);
        if (claim != null && claim.containsKey("roles")){
            List<String> roles = (List<String>) claim.get("roles");
            for (String role: roles){
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
        }

        if (resource != null) {
            Map<String, Object> resourceAccess = jwt.getClaim(claimName);
            if (resourceAccess != null && resourceAccess.containsKey(resource)) {
                List<String> resourceRoles = (List<String>) ((Map<String, Object>) resourceAccess.get(resource)).get("roles");
                if (resourceRoles != null) {
                    for (String role: resourceRoles) {
                        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                    }
                }
            }
        }
    }
}