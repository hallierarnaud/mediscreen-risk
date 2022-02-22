package com.openclassrooms.mediscreenrisk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.openclassrooms.mediscreenrisk")
public class MediscreenRiskApplication {

  public static void main(String[] args) {
    SpringApplication.run(MediscreenRiskApplication.class, args);
  }

}
