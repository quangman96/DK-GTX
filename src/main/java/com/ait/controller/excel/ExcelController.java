package com.ait.controller.excel;

import com.ait.excel.DataExport;
import com.ait.model.*;
import com.ait.service.JDBC.*;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class ExcelController {
    @Autowired
    CustomerJDBC customerService;

    @Autowired
    VehicleJDBC vehicleService;

    @Autowired
    ProvinceJDBC provinceService;

    @Autowired
    BrandJDBC brandService;

    @Autowired
    ColorJDBC colorService;

    @GetMapping("/excel/data")
    public void downLoadExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename= data.xlsx");
        ByteArrayInputStream stream = DataExport.listToExcelFile(createCustomerList(), createVehicleList(),
                createProvinceList(),createBrandList(),
                createColorList());
        IOUtils.copy(stream, response.getOutputStream());
    }

    private List<Customer> createCustomerList(){
        List<Customer> customers = customerService.findAll();
        return customers;
    }

    private List<Vehicle> createVehicleList(){
        List<Vehicle> vehicles = vehicleService.findAll();
        return vehicles;
    }

    private List<Province> createProvinceList(){
        List<Province> provinces = provinceService.findAll();
        return provinces;
    }
    private List<Brand> createBrandList(){
        List<Brand> brands = brandService.findAll();
        return brands;
    }

    private List<Color> createColorList(){
        List<Color> colors = colorService.findAll();
        return colors;
    }

}
