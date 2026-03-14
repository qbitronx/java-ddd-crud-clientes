package com.example.productosapi.infrastructure.rest.dto;

import com.example.productosapi.domain.model.TipoDocIdentidad;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {
    private UUID id;
    private String codigo;
    private TipoDocIdentidad tipoDocIdentidad;
    private String nroDocIdentidad;
    private String razonSocial;
    private String direccion;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
