package com.car_rental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car_rental.mongo.entity.Owner;
import com.car_rental.service.OwnerService;

@RestController
@RequestMapping("/owners")
@CrossOrigin(origins = "http://localhost:5173")
public class OwnerController {
    
    @Autowired
    private OwnerService ownerService;
    
    @GetMapping("")
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> owners = ownerService.getAllOwners();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable String id) {
        Owner owner = ownerService.getOwnerById(id);
        if (owner != null) {
            return new ResponseEntity<>(owner, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/saveowner")
    public ResponseEntity<Owner> saveOwner(@RequestBody Owner owner) {
        try {
            Owner savedOwner = ownerService.saveOwner(owner);
            return new ResponseEntity<>(savedOwner, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable String id, @RequestBody Owner owner) {
        Owner updatedOwner = ownerService.updateOwner(id, owner);
        if (updatedOwner != null) {
            return new ResponseEntity<>(updatedOwner, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable String id) {
        try {
            ownerService.deleteOwner(id);
            return new ResponseEntity<>("Owner deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting owner: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<Owner> getOwnerByEmail(@PathVariable String email) {
        Owner owner = ownerService.getOwnerByEmail(email);
        if (owner != null) {
            return new ResponseEntity<>(owner, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/contact/{contactNo}")
    public ResponseEntity<Owner> getOwnerByContactNo(@PathVariable String contactNo) {
        Owner owner = ownerService.getOwnerByContactNo(contactNo);
        if (owner != null) {
            return new ResponseEntity<>(owner, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
