package com.optimus.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class FinancialTx {

    private String userEmail;
    private Timestamp dateCreated;
    private String balance;
    private String activationCode;
    private String firstName;
    private String lastName;
}
