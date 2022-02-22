package com.openclassrooms.mediscreenrisk.proxies;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "mediscreen-note", url = "")
public class NoteProxy {
}
