package com.optimus.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "VENDOR_HISTORY")
public class VendorHistory extends AbstractUuidPersistable {

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "search_date")
    private ZonedDateTime searchDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "VENDOR_ID")
    private Vendor vendor;
}
