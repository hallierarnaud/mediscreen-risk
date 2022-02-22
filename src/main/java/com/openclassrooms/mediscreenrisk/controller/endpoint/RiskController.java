package com.openclassrooms.mediscreenrisk.controller.endpoint;

import com.openclassrooms.mediscreenrisk.domain.service.RiskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class RiskController {

  @Autowired
  private RiskService riskService;

  /**
   * @param patientId a patient's id
   * @return the risk corresponding to the patient
   */
  @GetMapping("/risks/{patientId}")
  public String getRiskByPatientId(@PathVariable("patientId") long patientId) {
    try {
      return riskService.getRisk(patientId);
    } catch (NoSuchElementException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "patient " + patientId + " doesn't exist");
    }
  }

}
