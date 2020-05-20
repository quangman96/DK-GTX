package com.ait.controller.rest;

import com.ait.model.Brand;
import com.ait.service.JDBC.BrandJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BrandController {
    @Autowired
    BrandJDBC brandService;

    @GetMapping("/brands")
    public List<Brand> brands(){
        return brandService.findAll();
    }

    @GetMapping("/brands/{id}")
    public Brand brands(@PathVariable Long id){
        return brandService.findById(id);
    }

    @PostMapping("/brands")
    public Brand addBrand(@RequestBody Brand brand){
        brandService.save(brand);
        return brand;
    }

    @PutMapping("/brands/{id}")
    public Brand updateBrand(@PathVariable Long id, @RequestBody Brand brand){
        Brand brand1 = brandService.findById(id);
        brand1.setName(brand.getName());
        brand1.setId(id);
        brandService.update(brand1);
        return brand;
    }

    @PutMapping("/brands/delete/{id}")
    public void removeBrand(@PathVariable Long id){
        brandService.remove(id);
    }
}
