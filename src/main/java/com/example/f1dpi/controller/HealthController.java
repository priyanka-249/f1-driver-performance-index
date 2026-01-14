package com.example.f1dpi.controller;

import com.example.f1dpi.Driver;
import com.example.f1dpi.service.DriverService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class HealthController {

    private final DriverService driverService = new DriverService();

    @GetMapping("/drivers")
    public List<Driver> getDrivers() {
        return driverService.getRankedDrivers();
    }
}


