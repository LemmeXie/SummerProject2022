package com.company.bankinksp.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "CLIENT", indexes = {
        @Index(name = "IDX_CLIENT_BANK_ID", columnList = "BANK_ID")
})
@JmixEntity
@Entity
public class Client {

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;


    @Column(name = "PHONE_NUMBER", nullable = false)
    @NotNull
    private BigDecimal phoneNumber;

    @Column(name = "EMAIL", nullable = false)
    @NotNull
    private String email;

    @Column(name = "PASSPORT_NUMBER", nullable = false, length = 20)
    @NotNull
    private String passportNumber;

    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "BANK_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Bank bank;

    public void setPhoneNumber(BigDecimal phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getPhoneNumber() {
        return phoneNumber;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}