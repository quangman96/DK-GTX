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
    public List<Customer> customerList() {
        return customerService.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer findCustomerById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @GetMapping("/identity")
    public List identityList() {
        return customerService.identityList();
    }

    @GetMapping("/customers/check/{identity}")
    public Customer findCustomerByIdentity(@PathVariable String identity) {
        return customerService.findByIdentity(identity);
    }

    @PostMapping("/customers")
    public Customer createNewCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
        return customer;
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer customerNew = customerService.findById(id);
        customerNew.setName(customer.getName());
        customerNew.setAddress(customer.getAddress());
        customerNew.setPhone(customer.getPhone());
        customerNew.setIdentity(customer.getIdentity());
        customerNew.setProvince_id(customer.getProvince_id());
        customerNew.setId(id);
        customerService.update(customerNew);
        return customer;
    }

    @PutMapping("/customers/delete/{id}")
    public void removeCustomer(@PathVariable Long id) {
        customerService.remove(id);
    }
}
