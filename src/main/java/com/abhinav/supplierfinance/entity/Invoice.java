package com.abhinav.supplierfinance.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.io.File;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Invoice {

    @Id
    @NotNull(message = "Invoice id is required")
    private String id;

    @NotNull(message = "Client username is required")
    private String clientUsername;

    @NotNull(message = "Supplier code is required")
    private String supplierCode;

    private String invoiceNumber;

    private String invoiceDate;

    private Double invoiceAmount;

    private String currency;

    private String status = "AWAITING";

    @Lob
    private byte[] invoiceFileContent;
}