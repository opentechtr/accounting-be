package com.otcp.accounting.currentAccount.entity;

import com.otcp.accounting.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "current_account")
@Getter
@Setter
@NoArgsConstructor
public class CurrentAccount extends BaseEntity {


    @Column(nullable = false, unique = true, length = 50)
    private String currentCode;

    @Column(nullable = false, length = 100)
    private String currentName;

    @Column(length = 100)
    private String currentName2;

    @Column(length = 50)
    private String currentType;

    @Column(length = 100)
    private String taxOffice;

    @Column(length = 11, unique = true)
    private String taxNumber;

    @Column(length = 255)
    private String invoiceAddress;

    @Column(length = 10)
    private String postalCode;

    @Column(length = 100)
    private String district;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String country;

    @Column(length = 20)
    private String phone1;

    @Column(length = 20)
    private String phone2;

    @Column(length = 20)
    private String mobilePhone;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 255)
    private String website;

    @Column(length = 20)
    private String fax;

    @Column(length = 34, unique = true)
    private String iban;

    @Column(length = 50)
    private String bankCode;

    @Column(length = 100)
    private String bankName;

    @Column(length = 50)
    private String bankBranchCode;

    @Column(length = 100)
    private String bankBranchName;

    @Column(length = 50)
    private String bankAccountNo;

    @Column(length = 10)
    private String currency;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private boolean inactive;

    @Column(length = 50)
    private String addressType;

    @Column(length = 255)
    private String shippingAddress;

    @Column(length = 10)
    private String shippingPostalCode;

    @Column(length = 20)
    private String shippingPhone1;

    @Column(length = 20)
    private String shippingPhone2;

    @Column(length = 20)
    private String shippingMobilePhone;

    @Column(length = 100)
    private String shippingEmail;

    @Column(length = 255)
    private String shippingWebsite;

    @Column(length = 20)
    private String shippingFax;

    @Column(length = 100)
    private String department;

    @Column(length = 50)
    private String authorizedPersonFirstName;

    @Column(length = 50)
    private String authorizedPersonLastName;

    @Column(length = 20)
    private String authorizedPersonMobilePhone1;

    @Column(length = 20)
    private String authorizedPersonMobilePhone2;


}
