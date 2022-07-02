package com.mediscreen.UserInterfaceApp.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class Patient {

    private Integer id;

    @NotBlank(message = "Firstname is mandatory")
    private String firstname;

    @NotBlank(message = "Lastname is mandatory")
    private String lastname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    @NotNull(message = "Date of born is mandatory")
    private LocalDate dateOfBorn;

    @NotBlank(message = "Gender is mandatory")
    @Pattern(regexp = "^(M|F|word)$")
    private String sex;

    private String address;

    private String phoneNumber;

    private String family;

    private String given;



    public Patient() {
    }
    public Patient(String family, String given, LocalDate dateOfBorn, String sex, String address, String phoneNumber) {
        this.family      = family;
        this.given       = given;
        this.dateOfBorn  = dateOfBorn;
        this.sex         = sex;
        this.address     = address;
        this.phoneNumber = phoneNumber;
    }

    public Patient(String firstname, String lastname, LocalDate dateOfBorn, String sex) {

        this.firstname  = firstname;
        this.lastname   = lastname;
        this.dateOfBorn = dateOfBorn;
        this.sex        = sex;
    }
}
