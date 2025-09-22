package com.car_rental.repo.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.car_rental.mongo.entity.Owner;

@Repository
public interface OwnerRepository extends MongoRepository<Owner, String> {
    // Custom query methods can be added here
    Owner findByEmail(String email);
    Owner findByContactNo(String contactNo);
}