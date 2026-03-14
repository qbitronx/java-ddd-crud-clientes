package com.example.productosapi.infrastructure.persistence.repository;

import com.example.productosapi.infrastructure.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, UUID> {
    Optional<ClienteEntity> findByCodigo(String codigo);
    boolean existsByCodigo(String codigo);
    boolean existsByNroDocIdentidad(String nroDocIdentidad);
}
