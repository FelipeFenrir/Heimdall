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
DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS oauth_client_token;
CREATE TABLE oauth_client_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS oauth_access_token;
CREATE TABLE oauth_access_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BLOB,
  refresh_token VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS oauth_refresh_token;
CREATE TABLE oauth_refresh_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication BLOB
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS oauth_code;
CREATE TABLE oauth_code (
  code VARCHAR(255),
  authentication BLOB
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS oauth_approvals;
CREATE TABLE oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;