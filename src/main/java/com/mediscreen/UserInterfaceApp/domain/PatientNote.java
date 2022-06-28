package com.mediscreen.UserInterfaceApp.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
public class PatientNote {

    private String noteId;

    private Integer patientId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotBlank(message = "Practitioner identity is mandatory")
    private String practitionerName;

    @NotBlank(message = "Practitioner's note is mandatory")
    private String note;

    public PatientNote( Integer patientId, LocalDate date, String practitionerName, String note) {
        this.patientId = patientId;
        this.date = date;
        this.practitionerName = practitionerName;
        this.note = note;
    }

    public PatientNote(Integer patientId) {
        this.patientId = patientId;
    }

    public PatientNote() {

    }
}
