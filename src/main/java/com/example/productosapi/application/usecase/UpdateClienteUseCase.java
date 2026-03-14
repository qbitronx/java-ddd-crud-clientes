package com.example.productosapi.application.usecase;

import com.example.productosapi.domain.model.Cliente;
import com.example.productosapi.domain.model.TipoDocIdentidad;
import com.example.productosapi.domain.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateClienteUseCase {

    private final ClienteService clienteService;

    public Cliente execute(UUID id, TipoDocIdentidad tipoDocIdentidad, String nroDocIdentidad,
                           String razonSocial, String direccion) {
        return clienteService.actualizarCliente(id, tipoDocIdentidad, nroDocIdentidad, razonSocial, direccion);
    }

    public Cliente executeEstado(UUID id, Boolean activo) {
        return clienteService.actualizarEstadoCliente(id, activo);
    }
}