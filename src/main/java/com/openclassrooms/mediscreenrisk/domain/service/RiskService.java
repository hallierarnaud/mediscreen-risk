package com.openclassrooms.mediscreenrisk.domain.service;

import com.openclassrooms.mediscreenrisk.domain.object.Patient;
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

  public Patient getPatientByPatientId(long patientId) {
    Patient patient = patientProxy.getPatientById(patientId);
    return patient;
  }

  public String getRisk (long patientId) {
    return getPatientByPatientId(patientId).getSex();
  }

}
