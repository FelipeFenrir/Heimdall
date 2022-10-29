/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package com.heimdall.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 *      ResetEmailToken Data Mapper class from dataprovider layer.
 * </p>
 *
 * @author Felipe de Andrade Batista
 */
@Entity
@Table(name = "email_reset_token")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings({"PersistenceUnitPresent", "ValidAttributes"})
public class ResetEmailTokenDataEntity extends BaseDataEntity implements Serializable {

    private static final long serialVersionUID = 3279872336895951317L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "nativeGenerator")
    @GenericGenerator(name = "nativeGenerator", strategy = "native")
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "pending_email", nullable = false)
    private String pendingEmail;

    @Column(name = "resetmail_token", nullable = false, unique = true)
    private String resetEmailToken;

    @OneToOne(targetEntity = UserDataEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDataEntity userDataMapper;

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
    public void setExpiryDate(long minutes) {
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
