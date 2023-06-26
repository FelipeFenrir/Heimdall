-- Permissions
DROP TABLE IF EXISTS permissions;
CREATE TABLE permissions (
    id BIGINT PRIMARY KEY auto_increment NOT NULL,
    name VARCHAR(60) UNIQUE,
    version BIGINT NOT NULL DEFAULT '0',
    created_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Roles
DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
    id BIGINT PRIMARY KEY auto_increment NOT NULL,
    name VARCHAR(60) UNIQUE,
    version BIGINT NOT NULL DEFAULT '0',
    created_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Permissions_Roles
DROP TABLE IF EXISTS permissions_roles;
CREATE TABLE permissions_roles(
    permission_id BIGINT,
    FOREIGN KEY(permission_id) REFERENCES permissions(id),
    role_id BIGINT,
    FOREIGN KEY(role_id) REFERENCES roles(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Users
DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id BIGINT PRIMARY KEY auto_increment NOT NULL,
    username VARCHAR(24) UNIQUE NOT NULL,
    email VARCHAR(128) UNIQUE NOT NULL,
    pending_email VARCHAR(128),
    password VARCHAR(256),
    tenant_id VARCHAR(256) NOT NULL,
    enabled BOOL NOT NULL,
    account_expired BOOL NOT NULL,
    credentials_expired BOOL NOT NULL,
    account_locked BOOL NOT NULL,
    confirmation_token VARCHAR(256),
    acept_term BOOL NOT NULL,
    version BIGINT NOT NULL DEFAULT '0',
    created_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Roles_Users
DROP TABLE IF EXISTS roles_users;
CREATE TABLE roles_users (
    role_id BIGINT,
    FOREIGN KEY(role_id) REFERENCES roles(id),
    user_id BIGINT,
    FOREIGN KEY(user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Permission Reset Token
DROP TABLE IF EXISTS password_reset_token;
CREATE TABLE password_reset_token (
    id BIGINT PRIMARY KEY auto_increment NOT NULL,
    resetpass_token VARCHAR(256),
    user_id BIGINT,
    FOREIGN KEY(user_id) REFERENCES users(id),
    expiry_date DATETIME,
    version BIGINT NOT NULL DEFAULT '0',
    created_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- OAuth
DROP TABLE IF EXISTS client;
CREATE TABLE client (
    id varchar(255) NOT NULL,
    clientId varchar(255) NOT NULL,
    clientIdIssuedAt timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    clientSecret varchar(255) DEFAULT NULL,
    clientSecretExpiresAt timestamp DEFAULT NULL,
    clientName varchar(255) NOT NULL,
    clientAuthenticationMethods varchar(1000) NOT NULL,
    authorizationGrantTypes varchar(1000) NOT NULL,
    redirectUris varchar(1000) DEFAULT NULL,
    postLogoutRedirectUris varchar(1000) DEFAULT NULL,
    scopes varchar(1000) NOT NULL,
    clientSettings varchar(2000) NOT NULL,
    tokenSettings varchar(2000) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS authorization;
CREATE TABLE authorization (
    id varchar(255) NOT NULL,
    registeredClientId varchar(255) NOT NULL,
    principalName varchar(255) NOT NULL,
    authorizationGrantType varchar(255) NOT NULL,
    authorizedScopes varchar(1000) DEFAULT NULL,
    attributes varchar(4000) DEFAULT NULL,
    state varchar(500) DEFAULT NULL,
    authorizationCodeValue varchar(4000) DEFAULT NULL,
    authorizationCodeIssuedAt timestamp DEFAULT NULL,
    authorizationCodeExpiresAt timestamp DEFAULT NULL,
    authorizationCodeMetadata varchar(2000) DEFAULT NULL,
    accessTokenValue varchar(4000) DEFAULT NULL,
    accessTokenIssuedAt timestamp DEFAULT NULL,
    accessTokenExpiresAt timestamp DEFAULT NULL,
    accessTokenMetadata varchar(2000) DEFAULT NULL,
    accessTokenType varchar(255) DEFAULT NULL,
    accessTokenScopes varchar(1000) DEFAULT NULL,
    refreshTokenValue varchar(4000) DEFAULT NULL,
    refreshTokenIssuedAt timestamp DEFAULT NULL,
    refreshTokenExpiresAt timestamp DEFAULT NULL,
    refreshTokenMetadata varchar(2000) DEFAULT NULL,
    oidcIdTokenValue varchar(4000) DEFAULT NULL,
    oidcIdTokenIssuedAt timestamp DEFAULT NULL,
    oidcIdTokenExpiresAt timestamp DEFAULT NULL,
    oidcIdTokenMetadata varchar(2000) DEFAULT NULL,
    oidcIdTokenClaims varchar(2000) DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS authorizationConsent;
CREATE TABLE authorizationConsent (
    registeredClientId varchar(255) NOT NULL,
    principalName varchar(255) NOT NULL,
    authorities varchar(1000) NOT NULL,
    PRIMARY KEY (registeredClientId, principalName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
