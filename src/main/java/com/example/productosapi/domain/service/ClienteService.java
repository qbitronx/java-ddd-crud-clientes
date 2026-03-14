package com.example.productosapi.domain.service;

import com.example.productosapi.domain.model.Cliente;
import com.example.productosapi.domain.model.TipoDocIdentidad;

import java.util.List;
import java.util.UUID;

public interface ClienteService {
    Cliente crearCliente(Cliente cliente);
    Cliente obtenerClientePorId(UUID id);
    Cliente obtenerClientePorCodigo(String codigo);
    List<Cliente> obtenerTodosLosClientes();
    Cliente actualizarCliente(UUID id, TipoDocIdentidad tipoDocIdentidad, String nroDocIdentidad,
                              String razonSocial, String direccion);
    Cliente actualizarEstadoCliente(UUID id, Boolean activo);
    void eliminarCliente(UUID id);
}
