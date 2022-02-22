package com.openclassrooms.mediscreenrisk.proxies;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "mediscreen-patient", url = "")
public class PatientProxy {
}
