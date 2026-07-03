package com.tourism.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {
    @GetMapping("/api/status")
    public Map<String, String> status() {
        return Map.of("application", "Smart Tourism Information Portal", "status", "UP");
    }
}
