package com.optimus.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class VendorHistoryDto {
    private String vendor;
    private String firstName;
    private String lastName;
    private String email;
    private ZonedDateTime startDate;
}
