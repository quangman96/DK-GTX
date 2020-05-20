package com.ait.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BaseService<T> {
    List<T> findAll();
    T findById(Long id);
    void save(T t);
    void update( T t);
    void remove(Long id);
}

