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
    public List<Vehicle> vehicleList(){
        return vehicleService.findAll();
    }

    @GetMapping("/vehicles/{id}")
    public Vehicle findVehicleById(@PathVariable Long id){
        return vehicleService.findById(id);
    }

    @PostMapping("/vehicles")
    public Vehicle addNewVehicle(@RequestBody Vehicle vehicle){
        vehicleService.save(vehicle);
        return vehicle;
    }

    @PutMapping("/vehicles/{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle){
        Vehicle vehicle1 = vehicleService.findById(id);
        vehicle1.setVehicle_name(vehicle.getVehicle_name());
        vehicle1.setEngine_num(vehicle.getEngine_num());
        vehicle1.setChassis_num(vehicle.getChassis_num());
        vehicle1.setCustomer_id(vehicle.getCustomer_id());
        vehicle1.setBrand_id(vehicle.getBrand_id());
        vehicle1.setColor_id(vehicle.getColor_id());
        vehicle1.setId(id);
        vehicleService.update(vehicle1);
        return vehicle;
    }

    @PutMapping("/vehicles/delete/{id}")
    public void removeVehicle(@PathVariable Long id){
        vehicleService.remove(id);
    }

    @GetMapping("/vehicles/check/{engine}/{chassis}")
    public Vehicle findByEngineOrChassisNumber(@PathVariable String engine, @PathVariable String chassis){
        return vehicleService.findByEngineOrChassisNumber(engine,chassis);
    }
}


