package com.car_rental.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "drivers") 
public class Driver {

    @Id
    private String _id; 

    private String name;
    private String licenseNo;
    private String phone;
    private String email;
    private String address;
    private String status;

    public Driver() {}

    public Driver(String driverId, String name, String licenseNo, String phone, String email, String address, String status) {
        this._id = driverId;
        this.name = name;
        this.licenseNo = licenseNo;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.status = status;
    }

    public String getDriverId() {
        return _id;
    }

    public void setDriverId(String driverId) {
        this._id = driverId;
    }

    // Standard getter for MongoDB serialization
    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLicenseNo() { return licenseNo; }
    public void setLicenseNo(String licenseNo) { this.licenseNo = licenseNo; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Driver{_id='" + _id + "', name='" + name + "', licenseNo='" + licenseNo + "'}";
    }
}
