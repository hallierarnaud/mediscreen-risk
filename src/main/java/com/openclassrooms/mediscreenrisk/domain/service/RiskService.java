package com.openclassrooms.mediscreenrisk.domain.service;

import com.openclassrooms.mediscreenrisk.proxies.NoteProxy;
import com.openclassrooms.mediscreenrisk.proxies.PatientProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiskService {

  @Autowired
  private PatientProxy patientProxy;

  @Autowired
  private NoteProxy noteProxy;

  public String getRiskByPatientId(final Long patientId) {
    return "";
  }

}
