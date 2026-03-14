package com.example.productosapi.domain.event;

import com.example.productosapi.domain.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ClienteEvent {

    public enum TipoEvento {
        CREADO, ACTUALIZADO, ELIMINADO, ESTADO_CAMBIADO
    }

    private final UUID id;
    private final TipoEvento tipo;
    private final Cliente cliente;
    private final LocalDateTime timestamp;
}
