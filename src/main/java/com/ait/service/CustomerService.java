package com.ait.service;

import com.ait.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    Customer findByIdentity(String identity);
    void save(Customer customer);
    void update(Customer customer);
    void remove(Long id);
}
