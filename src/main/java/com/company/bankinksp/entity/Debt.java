package com.company.bankinksp.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table(name = "DEBT", indexes = {
        @Index(name = "IDX_DEBT_BANK_ID", columnList = "BANK_ID")
})
@JmixEntity
@Entity
public class Debt {

    @Column(name = "LIMIT_", nullable = false)
    @NotNull
    private Double limit;

    @Column(name = "INTEREST_RATE", nullable = false)
    @NotNull
    private Double interestRate;

    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "BANK_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Bank bank;

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

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    @InstanceName
    @DependsOnProperties({"limit", "interestRate"})
    public String getInstanceName() {
        return String.format("Limit: %s, rate: %s", limit, interestRate);
    }
}