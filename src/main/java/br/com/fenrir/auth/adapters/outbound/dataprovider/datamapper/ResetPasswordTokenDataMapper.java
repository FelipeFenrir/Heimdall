/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */
package br.com.fenrir.auth.adapters.outbound.dataprovider.datamapper;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *      ResetPasswordToken Data Mapper class from dataprovider layer.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Entity
@Table(name = "password_reset_token")
@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings({"PersistenceUnitPresent", "ValidAttributes"})
public class ResetPasswordTokenDataMapper extends BaseDataMapper implements Serializable {

    private static final long serialVersionUID = 8504541314353467093L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "nativeGenerator")
    @GenericGenerator(name = "nativeGenerator", strategy = "native")
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "resetpass_token", nullable = false, unique = true)
    private String resetpassToken;

    @OneToOne(targetEntity = UserDataMapper.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDataMapper userDataMapper;

    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;

    /**
     * Get Expiry Date.
     * @return Expiry Date
     */
    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    /**
     * Set Expiry Date.
     * @param expiryDate Expiry Date
     */
    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Set Expiry Date in minutes.
     * @param minutes Expiry Date in minutes
     */
    public void setExpiryDate(int minutes) {
        this.expiryDate = LocalDateTime.now().plusMinutes(minutes);
    }

    /**
     * Verify if Reset Token is Expired.
     * @return True or False
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiryDate);
    }
}
