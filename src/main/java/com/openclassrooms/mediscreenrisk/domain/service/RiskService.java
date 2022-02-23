package com.openclassrooms.mediscreenrisk.domain.service;

import com.openclassrooms.mediscreenrisk.domain.object.Note;
import com.openclassrooms.mediscreenrisk.domain.object.Patient;
import com.openclassrooms.mediscreenrisk.proxies.NoteProxy;
import com.openclassrooms.mediscreenrisk.proxies.PatientProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

@Service
public class RiskService {

  @Autowired
  private PatientProxy patientProxy;

  @Autowired
  private NoteProxy noteProxy;

  public Integer calculatePatientAge(long patientId) {
    int age;
    LocalDate today = LocalDate.now();
    LocalDate birthdate = patientProxy.getPatientById(patientId).getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    Period period = Period.between(birthdate, today);
    age = period.getYears();
    return age;
  }

  public Integer getTriggerCount(long patientId) {
    int triggerCount = 0;
    List<String> triggers = Arrays.asList("Hémoglobine A1C", "Microalbumine", "Taille","Poids",
            "Fumeur", "Anormal", "Cholestérol", "Vertige", "Rechute", "Réaction", "Anticorps");
    List<Note> notes = noteProxy.getNotesByPatientId(patientId);
    for (Note note: notes) {
      for (String trigger: triggers) {
        if (note.getPatientNote().contains(trigger)) {
          ++triggerCount;
        }
      }
    }
    return triggerCount;
  }

  public String getRisk (long patientId) {
    String risk = "None";
    if (getTriggerCount(patientId) >= 2 && calculatePatientAge(patientId) > 30) {
      risk = "Borderline";
    }
    if ((patientProxy.getPatientById(patientId).getSex().equals("M") && calculatePatientAge(patientId) < 30
            && getTriggerCount(patientId) >= 3) || (patientProxy.getPatientById(patientId).getSex().equals("F")
            && calculatePatientAge(patientId) < 30 && getTriggerCount(patientId) >= 4) ||
            (calculatePatientAge(patientId) > 30 && getTriggerCount(patientId) >= 6)) {
      risk = "In Danger";
    }
    if ((patientProxy.getPatientById(patientId).getSex().equals("M") && calculatePatientAge(patientId) < 30
            && getTriggerCount(patientId) >= 5) || (patientProxy.getPatientById(patientId).getSex().equals("F")
            && calculatePatientAge(patientId) < 30 && getTriggerCount(patientId) >= 7) ||
            (calculatePatientAge(patientId) > 30 && getTriggerCount(patientId) >= 8)) {
      risk = "Early onset";
    }
    return risk;
  }

}
