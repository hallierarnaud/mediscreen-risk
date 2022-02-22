package com.openclassrooms.mediscreenrisk.proxies;

import com.openclassrooms.mediscreenrisk.domain.object.Patient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mediscreen-patient", url = "")
public interface PatientProxy {

  @GetMapping(value = "/patients/{id}")
  Patient getPatientById(@PathVariable long id);

}
