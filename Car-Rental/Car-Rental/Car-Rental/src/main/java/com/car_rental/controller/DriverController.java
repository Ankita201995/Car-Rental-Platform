package com.car_rental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.car_rental.mongo.entity.Driver;
import com.car_rental.service.DriverService;

@RestController
@RequestMapping("/drivers")
@CrossOrigin(origins = "http://localhost:5173")
public class DriverController {

    @Autowired
    private DriverService driverService;
    
    @GetMapping("")
    public List<Driver> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        System.out.println("Returning " + drivers.size() + " drivers");
        for (Driver driver : drivers) {
            System.out.println("Driver: " + driver.toString());
        }
        return drivers;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable String id) {
        return new ResponseEntity<>(driverService.getDriverById(id), HttpStatus.OK);
    }

    @PostMapping("/savedriver")
    public ResponseEntity<Driver> saveDriver(@RequestBody Driver driver) {
        return new ResponseEntity<>(driverService.saveDriver(driver), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable String id, @RequestBody Driver driver) {
        return new ResponseEntity<>(driverService.updateDriver(id, driver), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable String id) {
        try {
            driverService.deleteDriver(id);
            return new ResponseEntity<>("Driver deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting driver: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
