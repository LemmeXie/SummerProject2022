package com.company.bankinksp.entity;

import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "DEBT_OFFER", indexes = {
        @Index(name = "IDX_DEBTOFFER", columnList = "CLIENT_OFFERS_ID"),
        @Index(name = "IDX_DEBTOFFER_DEBT_OFFERS_ID", columnList = "DEBT_OFFERS_ID")
})
@Entity
public class DebtOffer {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "CLIENT_OFFERS_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Client clientOffers;

    @JoinColumn(name = "DEBT_OFFERS_ID", nullable = false)
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Debt debtOffers;

    @Column(name = "DEBT_SUM", nullable = false)
    @NotNull
    private Double debtSum;

    @Column(name = "PAYMENT_SCHEDULE", nullable = false)
    @NotNull
    private Integer paymentSchedule;

    @NotNull
    @Column(name = "DATE_PAYMENT", nullable = false)
    private LocalDate datePayment;

    @Column(name = "PAYMENT_SUM", nullable = false)
    @NotNull
    private Double paymentSum;

    @Column(name = "BODY_SUM", nullable = false)
    @NotNull
    private Double bodySum;

    @Column(name = "INTEREST_SUM", nullable = false)
    @NotNull
    private Double interestSum;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    public void setDatePayment(LocalDate datePayment) {
        this.datePayment = datePayment;
    }

    public LocalDate getDatePayment() {
        return datePayment;
    }

    public Double getInterestSum() {
        return interestSum;
    }

    public void setInterestSum(Double interestSum) {
        this.interestSum = interestSum;
    }

    public Double getBodySum() {
        return bodySum;
    }

    public void setBodySum(Double bodySum) {
        this.bodySum = bodySum;
    }

    public Double getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(Double paymentSum) {
        this.paymentSum = paymentSum;
    }

    public Integer getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(Integer paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public Double getDebtSum() {
        return debtSum;
    }

    public void setDebtSum(Double debtSum) {
        this.debtSum = debtSum;
    }

    public Debt getDebtOffers() {
        return debtOffers;
    }

    public void setDebtOffers(Debt debtOffers) {
        this.debtOffers = debtOffers;
    }

    public Client getClientOffers() {
        return clientOffers;
    }

    public void setClientOffers(Client clientOffers) {
        this.clientOffers = clientOffers;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}