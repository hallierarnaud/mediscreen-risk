package com.openclassrooms.mediscreenrisk.controller.endpoint;

import com.openclassrooms.mediscreenrisk.controller.DTO.RiskRequest;
import com.openclassrooms.mediscreenrisk.domain.service.RiskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
  public RiskRequest getRiskByPatientId(@PathVariable("patientId") long patientId) {
      return riskService.getRisk(patientId);
  }

}
