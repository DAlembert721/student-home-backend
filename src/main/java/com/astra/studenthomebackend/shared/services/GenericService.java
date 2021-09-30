package com.astra.studenthomebackend.shared.services;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, ID> {
    List<T> getAll();
    T getById(ID id);
    T create(T t);
    T update(ID id, T t);
    ResponseEntity<?> delete(ID id);
}
