package com.optimus.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "DEAL")
public class Deal extends AbstractUuidPersistable {

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "COUPON_CODE")
    private String couponCode;

    @Column(name = "EFFECTIVE_DATE")
    private ZonedDateTime effectiveDate;

    @Column(name = "EXPIRY_DATE")
    private ZonedDateTime expiryDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "VENDOR_ID")
    private Vendor vendor;
}




