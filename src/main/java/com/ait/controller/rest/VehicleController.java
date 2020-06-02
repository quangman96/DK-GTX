package com.ait.controller.rest;

import com.ait.model.Vehicle;
import com.ait.service.JDBC.VehicleJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleController {

    @Autowired
    VehicleJDBC vehicleService;

    @GetMapping("/vehicles")
    public List<Vehicle> vehicleList() {
        return vehicleService.findAll();
    }

    @GetMapping("/engine_num")
    public List engineNumberList() {
        return vehicleService.engineNumber();
    }

    @GetMapping("/chassis_num")
    public List chassisNumberList() {
        return vehicleService.chassisNumber();
    }

    @GetMapping("/vehicles/{id}")
    public Vehicle findVehicleById(@PathVariable Long id) {
        return vehicleService.findById(id);
    }

    @PostMapping("/vehicles")
    public Vehicle addNewVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.save(vehicle);
        return vehicle;
    }

    @PutMapping("/vehicles/{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        Vehicle vehicleNew = vehicleService.findById(id);
        vehicleNew.setVehicle_name(vehicle.getVehicle_name());
        vehicleNew.setEngine_num(vehicle.getEngine_num());
        vehicleNew.setChassis_num(vehicle.getChassis_num());
        vehicleNew.setCustomer_id(vehicle.getCustomer_id());
        vehicleNew.setBrand_id(vehicle.getBrand_id());
        vehicleNew.setColor_id(vehicle.getColor_id());
        vehicleNew.setId(id);
        vehicleService.update(vehicleNew);
        return vehicle;
    }

    @PutMapping("/vehicles/delete/{id}")
    public void removeVehicle(@PathVariable Long id) {
        vehicleService.remove(id);
    }

    @GetMapping("/vehicles/check/{engine}/{chassis}")
    public Vehicle findByEngineOrChassisNumber(@PathVariable String engine, @PathVariable String chassis) {
        return vehicleService.findByEngineOrChassisNumber(engine, chassis);
    }
}


