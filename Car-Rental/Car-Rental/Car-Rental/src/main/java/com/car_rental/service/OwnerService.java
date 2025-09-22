package com.car_rental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car_rental.mongo.entity.Owner;
import com.car_rental.repo.mongo.OwnerRepository;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepo;
    
    public List<Owner> getAllOwners() {
        return ownerRepo.findAll();
    }

    public Owner getOwnerById(String id) {
        return ownerRepo.findById(id).orElse(null);
    }

    public Owner saveOwner(Owner owner) {
        return ownerRepo.save(owner);
    }
    
    public Owner updateOwner(String id, Owner updatedOwner) {
        Optional<Owner> optionalOwner = ownerRepo.findById(id);
        if (optionalOwner.isPresent()) {
            Owner existingOwner = optionalOwner.get();
            existingOwner.setName(updatedOwner.getName());
            existingOwner.setContactNo(updatedOwner.getContactNo());
            existingOwner.setEmail(updatedOwner.getEmail());
            existingOwner.setAdminId(updatedOwner.getAdminId());
            return ownerRepo.save(existingOwner);
        } else {
            return null;
        }
    }

    public void deleteOwner(String id) {
        ownerRepo.deleteById(id);
    }
    
    public Owner getOwnerByEmail(String email) {
        return ownerRepo.findByEmail(email);
    }
    
    public Owner getOwnerByContactNo(String contactNo) {
        return ownerRepo.findByContactNo(contactNo);
    }
}
