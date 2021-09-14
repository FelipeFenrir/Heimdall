/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package br.com.fenrir.auth.adapters.outbound.dataprovider.datamapper;

import br.com.fenrir.commons.utils.DataUtil;

import java.io.Serializable;
import java.util.Calendar;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 *      User Data Mapper class from dataprovider layer.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings({"PersistenceUnitPresent", "ValidAttributes"})
public class UserDataMapper extends BaseDataMapper implements UserDetails, Serializable {

    private static final long serialVersionUID = 7191422918511326153L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "nativeGenerator")
    @GenericGenerator(name = "nativeGenerator", strategy = "native")
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Email
    @Column(name = "pending_email")
    private String pendingEmail;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "tenant_id", nullable = false, unique = true)
    private String tenantid;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Column(name = "acept_term", nullable = false)
    private boolean aceptTerm;

    @Column(name = "account_locked")
    private boolean accountNonLocked;

    @Column(name = "account_expired")
    private boolean accountNonExpired;

    @Column(name = "credentials_expired")
    private boolean credentialsNonExpired;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_users", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<RoleDataMapper> roleDataMappers;

    @Override
    public boolean isAccountNonExpired() {
        return !accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsNonExpired;
    }

    /*
     * Get roleDataMappers and permissions and add them as a Set of GrantedAuthority
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

//        roleDataMappers.forEach(r -> {
//            authorities.add(new SimpleGrantedAuthority(r.getName()));
//            r.getPermissionDataMappers().forEach(p -> {
//                authorities.add(new SimpleGrantedAuthority(p.getName()));
//            });
//        });

        roleDataMappers.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getPermissionDataMappers().forEach(
                    permission -> authorities.add(
                            new SimpleGrantedAuthority(permission.getName())
                    ));
        });

        return authorities;
    }

    /**
     * Verify if confirmation token is expired.
     *
     * @param minutes minutes of token expiration.
     * @return Token is Expired or not.
     */
    public boolean isExpired(int minutes) {
        Calendar expire = DataUtil.convertToCalendar(super.getCreatedOn());
        expire.add(Calendar.MINUTE, minutes);
        return new Date().after(DataUtil.convertToDate(expire));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
