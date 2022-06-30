package com.mediscreen.UserInterfaceApp.proxy;

import com.mediscreen.UserInterfaceApp.domain.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(contextId = "Patient" , name="patientApp", url="${patientAppUrl}")
public interface PatientAppProxy {

    @GetMapping("/patient/list")
    public List<Patient> getAllPatients();

    @GetMapping("/patient/get")
    public Patient getPatientByFirstnameAndLastname(@RequestParam String firstname,  @RequestParam String lastname);

    @PostMapping("/patient/add")
    public void addNewPatient(@RequestBody Patient patient);


    @GetMapping("/patient/get/{id}")
    public Patient getPatientById(@PathVariable Integer id);

    @PutMapping("/patient/update/{id}")
    public void updatePatientInfos(@PathVariable Integer id, @RequestBody Patient patient);


}
