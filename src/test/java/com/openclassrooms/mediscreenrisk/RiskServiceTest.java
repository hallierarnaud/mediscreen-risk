package com.openclassrooms.mediscreenrisk;

import com.openclassrooms.mediscreenrisk.domain.object.Note;
import com.openclassrooms.mediscreenrisk.domain.object.Patient;
import com.openclassrooms.mediscreenrisk.domain.service.RiskService;
import com.openclassrooms.mediscreenrisk.proxies.NoteProxy;
import com.openclassrooms.mediscreenrisk.proxies.PatientProxy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RiskServiceTest {

  @Mock
  private NoteProxy noteProxy;

  @Mock
  private PatientProxy patientProxy;

  @InjectMocks
  private RiskService riskService;

  @Test
  public void getPatientAge_shouldReturnOk() {
    // GIVEN
    Patient patient = new Patient();
    Date birthDate = new Date(100, 1, 1);
    patient.setBirthdate(birthDate);
    when(patientProxy.getPatientById(anyLong())).thenReturn(patient);

    // WHEN
    int actualAge = riskService.calculatePatientAge(anyLong());

    // THEN
    assertEquals(22, actualAge);
  }

  @Test
  public void getTriggerCount_shouldReturnOk() {
    // GIVEN
    List<Note> notes = new ArrayList<>();
    Note note = new Note();
    note.setPatientNote("Vertige");
    notes.add(note);
    when(noteProxy.getNotesByPatientId(anyLong())).thenReturn(notes);

    // WHEN
    int actualTriggerCount = riskService.getTriggerCount(anyLong());

    // THEN
    assertEquals(1, actualTriggerCount);

  }

}
