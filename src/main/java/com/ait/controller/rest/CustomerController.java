package com.ait.controller.rest;

import com.ait.model.Customer;
import com.ait.service.JDBC.CustomerJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    CustomerJDBC customerService;

    @GetMapping("/customers")
    public List<Customer> customerList(){
        return customerService.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer findCustomerById(@PathVariable Long id){
        return customerService.findById(id);
    }

    @GetMapping("/customers/identity/{identity}")
    public Customer findCustomerByIdentity(@PathVariable String identity){ return customerService.findByIdentity(identity);}

    @PostMapping("/customers")
    public Customer createNewCustomer(@RequestBody Customer customer){
        customerService.save(customer);
        return customer;
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        Customer customer1 = customerService.findById(id);
        customer1.setName(customer.getName());
        customer1.setAddress(customer.getAddress());
        customer1.setPhone(customer.getPhone());
        customer1.setIdentity(customer.getIdentity());
        customer1.setProvince_id(customer.getProvince_id());
        customer1.setId(id);
        customerService.update(customer1);
        return customer;
    }

    @PutMapping("/customers/delete/{id}")
    public void removeCustomer(@PathVariable Long id){
        customerService.remove(id);
    }
}
