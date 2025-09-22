package com.car_rental.repo.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.car_rental.mongo.entity.Driver;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String> {}

