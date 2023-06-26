package com.heimdall.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "authorization")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Authorization extends BaseDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    private String registeredClientId;

    private String principalName;

    private String authorizationGrantType;

    //@Column(columnDefinition="TEXT")
    private String attributes;

    @Column(length = 500)
    private String state;

//    @Column(columnDefinition="TEXT")
    private String authorizationCodeValue;

    private Instant authorizationCodeIssuedAt;

    private Instant authorizationCodeExpiresAt;

//    @Column(columnDefinition="TEXT")
    private String authorizationCodeMetadata;

//    @Column(columnDefinition="TEXT")
    private String accessTokenValue;

    private Instant accessTokenIssuedAt;

    private Instant accessTokenExpiresAt;

//    @Column(columnDefinition="TEXT")
    private String accessTokenMetadata;

    private String accessTokenType;

//    @Column(columnDefinition="TEXT")
    private String accessTokenScopes;

//    @Column(columnDefinition="TEXT")
    private String refreshTokenValue;

    private Instant refreshTokenIssuedAt;

    private Instant refreshTokenExpiresAt;

//    @Column(columnDefinition="TEXT")
    private String refreshTokenMetadata;

//    @Column(columnDefinition="TEXT")
    private String oidcIdTokenValue;

    private Instant oidcIdTokenIssuedAt;

    private Instant oidcIdTokenExpiresAt;

//    @Column(columnDefinition="TEXT")
    private String oidcIdTokenMetadata;

//    @Column(columnDefinition="TEXT")
    private String oidcIdTokenClaims;
}
