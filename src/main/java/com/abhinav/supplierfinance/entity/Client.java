package com.abhinav.supplierfinance.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @NotNull(message = "Username is required")
    private String username;

    @NotNull(message = "Password is required")
    private String password;

    @NotNull(message = "Name is required")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String name;

    @NotNull(message = "Address is required")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Address can only contain alphanumeric characters")
    private String address;

    @NotNull(message = "Country is required")
    private String country;

    @NotNull(message = "State is required")
    private String state;

    @NotNull(message = "City is required")
    private String city;

    @NotNull(message = "Province is required")
    private String province;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

    @NotNull(message = "Mobile number is required")
    @Pattern(regexp = "\\d{10}", message = "Mobile number must be a 10 digit number")
    private String mobileNumber;

    @NotNull(message = "Loan account number is required")
    @Pattern(regexp = "\\d{10}", message = "Loan account number must be a 10 digit number")
    private String loanAccountNumber;

    @NotNull(message = "Loan information is required")
    private String loanInformation;
}