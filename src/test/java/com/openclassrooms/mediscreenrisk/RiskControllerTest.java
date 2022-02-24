package com.openclassrooms.mediscreenrisk;

import com.openclassrooms.mediscreenrisk.controller.DTO.RiskRequest;
import com.openclassrooms.mediscreenrisk.controller.endpoint.RiskController;
import com.openclassrooms.mediscreenrisk.domain.service.RiskService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RiskController.class)
public class RiskControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RiskService riskService;

  @Test
  public void getRiskByPatientId_shouldReturnOk() throws Exception {
    RiskRequest riskRequest = new RiskRequest();
    when(riskService.getRisk(anyLong())).thenReturn(riskRequest);
    mockMvc.perform(get("/risks/1")).andExpect(status().isOk());
  }

}
