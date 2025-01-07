package com.optimus.model;

import net.minidev.json.annotate.JsonIgnore;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "VENDOR")
public class Vendor extends AbstractUuidPersistable {

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "WEBSITE")
    private String website;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LOGO")
    private String logo;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "START_DATE")
    private ZonedDateTime startDate;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
    @JsonIgnore
    private Category category;
}
