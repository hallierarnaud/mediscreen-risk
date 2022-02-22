package com.openclassrooms.mediscreenrisk.proxies;

import com.openclassrooms.mediscreenrisk.domain.object.Note;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "mediscreen-note", url = "${host.note}")
public interface NoteProxy {

  @GetMapping(value = "/notes/{patientId}")
  List<Note> getNotesByPatientId(@PathVariable long patientId);

}
