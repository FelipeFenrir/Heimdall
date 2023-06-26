INSERT INTO permissions (name, version, created_on, updated_on) VALUES
        ('CAN_CREATE',0,'1970-01-01 00:00:00','1970-01-01 00:00:00'),
        ('CAN_UPDATE',0,'1970-01-01 00:00:00','1970-01-01 00:00:00'),
        ('CAN_READ',0,'1970-01-01 00:00:00','1970-01-01 00:00:00'),
        ('CAN_DELETE',0,'1970-01-01 00:00:00','1970-01-01 00:00:00');

INSERT INTO roles (name, version, created_on, updated_on) VALUES
        ('ROLE_MASTER',0,'1970-01-01 00:00:00','1970-01-01 00:00:00'),
        ('ROLE_ADMIN',0,'1970-01-01 00:00:00','1970-01-01 00:00:00'),
        ('ROLE_USER',0,'1970-01-01 00:00:00','1970-01-01 00:00:00'),
        ('ROLE_SWAGGER',0,'1970-01-01 00:00:00','1970-01-01 00:00:00');

INSERT INTO permissions_roles (permission_id, role_id) VALUES
        (1,1), /* CAN_CREATE assigned to ROLE_MASTER */
        (2,1), /* CAN_UPDATE assigned to ROLE_MASTER */
        (3,1), /* CAN_READ assigned to ROLE_MASTER */
        (4,1), /* CAN_DELETE assigned to ROLE_MASTER */

        (1,2), /* CAN_CREATE assigned to ROLE_ADMIN */
        (2,2), /* CAN_UPDATE assigned to ROLE_ADMIN */
        (3,2), /* CAN_READ assigned to ROLE_ADMIN */

        (3,3),  /* CAN_READ assigned to ROLE_USER */

        (1,4), /* CAN_CREATE assigned to ROLE_SWAGGER */
        (2,4), /* CAN_UPDATE assigned to ROLE_SWAGGER */
        (3,4), /* CAN_READ assigned to ROLE_SWAGGER */
        (4,4); /* CAN_DELETE assigned to ROLE_SWAGGER */

INSERT INTO users (id, username, email, pending_email, password, tenant_id, enabled, account_expired, credentials_expired, account_locked, confirmation_token, acept_term, version, created_on, updated_on) VALUES
	    ('1', 'Fenrir', 'fenrir@fenrir.com', null, '{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi', 'akumos', true, false, false, false, null, true, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00'),
	    ('2', 'Admin', 'admin@fenrir.com', null, '{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi', 'akumos', true, false, false, false, null, true, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00'),
	    ('3', 'User', 'user@fenrir.com', null, '{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi', 'akumos', true, false, false, false, null, true, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00'),

        ('4', 'Testresetpass', 'testresetpass@fenrir.com', null, '{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi', 'akumos', true, false, false, false, null, true, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00'),
        ('5', 'Testforgotpass', 'testforgotpass@fenrir.com', null, '{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi', 'akumos', true, false, false, false, null, true, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00'),
        ('6', 'Testforgotemail', 'testforgotemail@fenrir.com', null, '{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi', 'akumos', true, false, false, false, null, true, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00'),

        ('7', 'Aegon Targaryen', 'aegon@targaryensa.com', null, '{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi', 'targaryensa', true, false, false, false, null, true, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00'),
        ('8', 'Daenerys Targaryen', 'daenerys@targaryensa.com', null, '{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi', 'targaryensa', true, false, false, false, null, true, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00'),

        ('9', 'Eddard Stark', 'eddard@starksa.com', null, '{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi', 'starksa', true, false, false, false, null, true, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00'),
        ('10', 'Jon Snow', 'jonsnow@starksa.com', null, '{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi', 'starksa', true, false, false, false, null, true, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');

INSERT INTO password_reset_token (id, resetpass_token, user_id, expiry_date, version, created_on, updated_on) VALUES
        (1, 'expired-token', 4, '2017-01-01 00:00:00', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00'),
        (2, 'valid-token', 4, '9000-01-01 00:00:00', 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00');

INSERT INTO roles_users (role_id, user_id) VALUES
        (1, 1) /* ROLE_MASTER assigned to Fenrir user */,
        (2, 1) /* ROLE_ADMIN assigned to Fenrir user */,
        (3, 1) /* ROLE_USER assigned to Fenrir user */,
        (4, 1) /* ROLE_SWAGGER assigned to Fenrir user */,

        (2, 2) /* ROLE_ADMIN assigned to Admin user */,
        (3, 2) /* ROLE_USER assigned to Admin user */,
        (4, 2) /* ROLE_SWAGGER assigned to Admin user */,

        (3, 3) /* ROLE_USER assigned to User user */,
        (4, 3) /* ROLE_SWAGGER assigned to User user */,

        (3, 4) /* ROLE_USER assigned to Testresetpass user */,
        (4, 4) /* ROLE_SWAGGER assigned to Testresetpass user */,

        (3, 5) /* ROLE_USER assigned to Testforgotpass user */,
        (4, 5) /* ROLE_SWAGGER assigned to Testforgotpass user */,

        (3, 6) /* ROLE_USER assigned to Testforgotemail user */,
        (4, 6) /* ROLE_SWAGGER assigned to Testforgotemail user */,

        (2, 7) /* ROLE_ADMIN assigned to Aegon Targaryen user */,
        (3, 7) /* ROLE_USER assigned to Aegon Targaryen user */,

        (3, 8) /* ROLE_USER assigned to Daenerys Stark user */,

        (2, 9) /* ROLE_ADMIN assigned to Eddard Stark user */,
        (3, 9) /* ROLE_USER assigned to Eddard Stark user */,

        (3, 10) /* ROLE_USER assigned to Jon Snow user */;
