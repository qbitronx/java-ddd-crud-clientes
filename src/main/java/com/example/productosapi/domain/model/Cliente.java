package com.example.productosapi.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidad de dominio para representar un Cliente.
 * Esta clase sigue los principios de inmutabilidad de DDD.
 */
@Getter
@ToString
@Builder
public class Cliente {
    private final UUID id;
    private final String codigo;
    private final TipoDocIdentidad tipoDocIdentidad;
    private final String nroDocIdentidad;
    private final String razonSocial;
    private final String direccion;
    private final Boolean activo;
    private final LocalDateTime fechaCreacion;
    private final LocalDateTime fechaActualizacion;

    /**
     * Método para actualizar los datos del cliente
     */
    public Cliente actualizar(TipoDocIdentidad tipoDocIdentidad, String nroDocIdentidad,
                              String razonSocial, String direccion) {
        return Cliente.builder()
                .id(this.id)
                .codigo(this.codigo)
                .tipoDocIdentidad(tipoDocIdentidad)
                .nroDocIdentidad(nroDocIdentidad)
                .razonSocial(razonSocial)
                .direccion(direccion)
                .activo(this.activo)
                .fechaCreacion(this.fechaCreacion)
                .fechaActualizacion(LocalDateTime.now())
                .build();
    }

    /**
     * Método para cambiar el estado activo del cliente
     */
    public Cliente cambiarEstado(Boolean activo) {
        return Cliente.builder()
                .id(this.id)
                .codigo(this.codigo)
                .tipoDocIdentidad(this.tipoDocIdentidad)
                .nroDocIdentidad(this.nroDocIdentidad)
                .razonSocial(this.razonSocial)
                .direccion(this.direccion)
                .activo(activo)
                .fechaCreacion(this.fechaCreacion)
                .fechaActualizacion(LocalDateTime.now())
                .build();
    }

    /**
     * Indica si el cliente está activo
     */
    public boolean estaActivo() {
        return Boolean.TRUE.equals(this.activo);
    }
}
