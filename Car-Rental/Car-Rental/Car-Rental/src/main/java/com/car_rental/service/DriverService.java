package com.car_rental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car_rental.mongo.entity.Driver;
import com.car_rental.repo.mongo.DriverRepository;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepo;
    
    public List<Driver> getAllDrivers() {
        List<Driver> drivers = driverRepo.findAll();
        System.out.println("DriverService: Found " + drivers.size() + " drivers from MongoDB");
        for (Driver driver : drivers) {
            System.out.println("DriverService: Driver ID = " + driver.getId() + ", Name = " + driver.getName());
        }
        return drivers;
    }

    public Driver getDriverById(String id) {
        return driverRepo.findById(id).orElse(null);
    }

    public Driver saveDriver(Driver driver) {
        return driverRepo.save(driver);
    }
    
    public Driver updateDriver(String id, Driver updatedDriver) {
        Optional<Driver> optionalDriver = driverRepo.findById(id);
        if (optionalDriver.isPresent()) {
            Driver existingDriver = optionalDriver.get();
            existingDriver.setName(updatedDriver.getName());
            existingDriver.setLicenseNo(updatedDriver.getLicenseNo());
            existingDriver.setPhone(updatedDriver.getPhone());
            existingDriver.setEmail(updatedDriver.getEmail());
            existingDriver.setAddress(updatedDriver.getAddress());
            existingDriver.setStatus(updatedDriver.getStatus());
            return driverRepo.save(existingDriver);
        } else {
            return null;
        }
    }

    public void deleteDriver(String id) {
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("Driver ID cannot be null or empty");
            }
            
            if (!driverRepo.existsById(id)) {
                throw new RuntimeException("Driver with ID " + id + " not found");
            }
            
            driverRepo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting driver with ID " + id + ": " + e.getMessage(), e);
        }
    }
}
