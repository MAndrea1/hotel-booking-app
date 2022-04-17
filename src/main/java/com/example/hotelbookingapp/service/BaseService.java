package com.example.hotelbookingapp.service;

import com.example.hotelbookingapp.model.Facility;

import java.util.List;
import java.util.Optional;

public interface BaseService<E,T> {
    public List<E> findAll() throws Exception;
    public Optional<E> findById(Integer id) throws Exception;
    public E save(T entity) throws Exception;
    public E update(Integer id , T Entity) throws Exception;
    public Boolean delete(Integer id) throws Exception;

}
