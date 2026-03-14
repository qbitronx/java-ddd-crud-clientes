package com.example.productosapi.domain.exception;

import java.util.UUID;

public class ClienteNotFoundException extends BusinessException {

    public ClienteNotFoundException(UUID id) {
        super("Cliente no encontrado con id: " + id);
    }

    public ClienteNotFoundException(String codigo) {
        super("Cliente no encontrado con código: " + codigo);
    }
}
