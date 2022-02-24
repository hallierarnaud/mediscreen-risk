package com.openclassrooms.mediscreenrisk.controller.DTO;

import lombok.Data;

@Data
public class RiskRequest {

  private String risk;
  private int age;
  private String sex;
  private int trigger;

}
