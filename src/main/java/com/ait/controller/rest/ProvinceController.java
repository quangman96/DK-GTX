package com.ait.controller.rest;

import com.ait.model.Province;
import com.ait.service.JDBC.ProvinceJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProvinceController {
    @Autowired
    ProvinceJDBC provinceService;

    @GetMapping("/provinces")
    public List<Province> provinceList(){
        return provinceService.findAll();
    }

    @GetMapping("/provinces/{id}")
    public Province findProvinceById(@PathVariable Long id){
        return provinceService.findById(id);
    }

    @PostMapping("/provinces")
    public Province addNewProvince(@RequestBody Province province){
        provinceService.save(province);
        return province;
    }

    @PutMapping("/provinces/{id}")
    public Province updateProvince(@PathVariable Long id, @RequestBody Province province){
        Province province1 = provinceService.findById(id);
        province1.setName(province.getName());
        province1.setCode(province.getCode());
        province1.setId(id);
        provinceService.update(province1);
        return province;
    }

    @PutMapping("/provinces/delete/{id}")
    public void removeColor(@PathVariable Long id){
        provinceService.remove(id);
    }
}

