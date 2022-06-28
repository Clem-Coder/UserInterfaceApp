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

}
