package com.mediscreen.UserInterfaceApp.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientReport {

    private String firstname;

    private String lastname;

    private int age;

    private String sex;

    private String address;

    private String phoneNumber;

    private String riskLevel;

    public PatientReport(){

    }

    public PatientReport(String firstname, String lastname, int age, String sex, String address, String phoneNumber, String riskLevel) {

        this.firstname   = firstname;
        this.lastname    = lastname;
        this.age         = age;
        this.sex         = sex;
        this.address     = address;
        this.phoneNumber = phoneNumber;
        this.riskLevel   = riskLevel;
    }
}
