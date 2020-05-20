package com.ait.controller;

import com.ait.model.Brand;
import com.ait.service.JDBC.BrandJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    BrandJDBC brandService;

    @GetMapping("/")
    public List<Brand> brands() {
        return brandService.findAll();
    }
}
