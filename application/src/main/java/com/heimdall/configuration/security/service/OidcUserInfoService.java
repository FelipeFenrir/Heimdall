package com.heimdall.configuration.security.service;

import com.heimdall.entrypoint.boundary.dto.implementations.UserDto;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service(value = "oidcUserInfoService")
public class OidcUserInfoService {
    private final UserDetailsService userDetailsService;

    public OidcUserInfo loadUser(String username) {
        UserDto user = (UserDto) userDetailsService.loadUserByUsername(username);
        return OidcUserInfo.builder()
            .subject(user.getId().toString())
            //.name(user.getFirstName().concat(" ").concat(user.getLastName()))
            //.givenName(user.getFirstName())
            //.familyName(user.getLastName())
            .nickname(username)
            .preferredUsername(username)
            //.profile("https://example.com/" + username)
            //.website("https://example.com")
            .email(user.getEmail())
            .emailVerified(true)
            .claim("tenant", user.getTenantid())
            .claim("roles", user.getRoles())
            //.zoneinfo("Europe/Berlin")
            //.locale("de-DE")
            //.updatedAt("1970-01-01T00:00:00Z")
            .build();
    }
}
