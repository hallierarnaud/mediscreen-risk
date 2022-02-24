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
  public void getRisk_shouldReturnNone() {
    // GIVEN
    Patient patient = new Patient();
    Date birthDate = new Date(100, 1, 1);
    patient.setBirthdate(birthDate);
    patient.setSex("M");
    when(patientProxy.getPatientById(anyLong())).thenReturn(patient);

    List<Note> notes = new ArrayList<>();
    Note note = new Note();
    note.setPatientNote("Vertige");
    notes.add(note);
    when(noteProxy.getNotesByPatientId(anyLong())).thenReturn(notes);

    // WHEN
    String actualRisk = riskService.getRisk(anyLong());

    // THEN
    assertEquals("None", actualRisk);
  }

  @Test
  public void getRisk_shouldReturnBorderline() {
    // GIVEN
    Patient patient = new Patient();
    Date birthDate = new Date(50, 1, 1);
    patient.setBirthdate(birthDate);
    patient.setSex("M");
    when(patientProxy.getPatientById(anyLong())).thenReturn(patient);

    List<Note> notes = new ArrayList<>();
    Note note = new Note();
    note.setPatientNote("Vertige Réaction");
    notes.add(note);
    when(noteProxy.getNotesByPatientId(anyLong())).thenReturn(notes);

    // WHEN
    String actualRisk = riskService.getRisk(anyLong());

    // THEN
    assertEquals("Borderline", actualRisk);
  }

  @Test
  public void getRisk_shouldReturnInDanger() {
    // GIVEN
    Patient patient = new Patient();
    Date birthDate = new Date(100, 1, 1);
    patient.setBirthdate(birthDate);
    patient.setSex("M");
    when(patientProxy.getPatientById(anyLong())).thenReturn(patient);

    List<Note> notes = new ArrayList<>();
    Note note = new Note();
    note.setPatientNote("Vertige Fumeur Réaction");
    notes.add(note);
    when(noteProxy.getNotesByPatientId(anyLong())).thenReturn(notes);

    // WHEN
    String actualRisk = riskService.getRisk(anyLong());

    // THEN
    assertEquals("In Danger", actualRisk);
  }

  @Test
  public void getRisk_shouldReturnEarlyOnset() {
    // GIVEN
    Patient patient = new Patient();
    Date birthDate = new Date(100, 1, 1);
    patient.setBirthdate(birthDate);
    patient.setSex("M");
    when(patientProxy.getPatientById(anyLong())).thenReturn(patient);

    List<Note> notes = new ArrayList<>();
    Note note = new Note();
    note.setPatientNote("Vertige Fumeur Réaction Rechute Poids");
    notes.add(note);
    when(noteProxy.getNotesByPatientId(anyLong())).thenReturn(notes);

    // WHEN
    String actualRisk = riskService.getRisk(anyLong());

    // THEN
    assertEquals("Early onset", actualRisk);
  }

}
