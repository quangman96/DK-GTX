package com.ait.controller.rest;

import com.ait.model.Brand;
import com.ait.service.JDBC.BrandJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BrandController {

    @Autowired
    BrandJDBC brandService;

    @GetMapping("/brands")
    public List<Brand> brands() {
        return brandService.findAll();
    }

    @GetMapping("/brands/{id}")
    public Brand brands(@PathVariable Long id) {
        return brandService.findById(id);
    }

    @PostMapping("/brands")
    public Brand addBrand(@RequestBody Brand brand) {
        brandService.save(brand);
        return brand;
    }

    @PutMapping("/brands/{id}")
    public Brand updateBrand(@PathVariable Long id, @RequestBody Brand brand) {
        Brand brandNew = brandService.findById(id);
        brandNew.setName(brand.getName());
        brandNew.setId(id);
        brandService.update(brandNew);
        return brand;
    }

    @PutMapping("/brands/delete/{id}")
    public void removeBrand(@PathVariable Long id) {
        brandService.remove(id);
    }
}
