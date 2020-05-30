package com.ait.service;

import com.ait.model.Customer;
import com.ait.model.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface VehicleService {
    List<Vehicle> findAll();
    Vehicle findById(Long id);
    void save(Vehicle vehicle);
    void update( Vehicle vehicle);
    void remove(Long id);
    Vehicle findByEngineOrChassisNumber(String engine_num, String chassis_num);
    List engineNumber();
    List chassisNumber();
//    List<Vehicle> statisticsByMonth();
//    List<Vehicle> statisticsByBrand();
}
