package com.example.productosapi.domain.repository;

import com.example.productosapi.domain.model.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository {
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(UUID id);
    Optional<Cliente> findByCodigo(String codigo);
    List<Cliente> findAll();
    void deleteById(UUID id);
    boolean existsByCodigo(String codigo);
    boolean existsByNroDocIdentidad(String nroDocIdentidad);
}
