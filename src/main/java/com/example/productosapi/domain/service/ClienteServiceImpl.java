package com.example.productosapi.domain.service;

import com.example.productosapi.domain.exception.BusinessException;
import com.example.productosapi.domain.exception.ClienteNotFoundException;
import com.example.productosapi.domain.model.Cliente;
import com.example.productosapi.domain.model.TipoDocIdentidad;
import com.example.productosapi.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public Cliente crearCliente(Cliente cliente) {
        if (clienteRepository.existsByCodigo(cliente.getCodigo())) {
            throw new BusinessException("Ya existe un cliente con el código: " + cliente.getCodigo());
        }
        if (clienteRepository.existsByNroDocIdentidad(cliente.getNroDocIdentidad())) {
            throw new BusinessException("Ya existe un cliente con el número de documento: " + cliente.getNroDocIdentidad());
        }
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente obtenerClientePorId(UUID id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente obtenerClientePorCodigo(String codigo) {
        return clienteRepository.findByCodigo(codigo)
                .orElseThrow(() -> new ClienteNotFoundException(codigo));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente actualizarCliente(UUID id, TipoDocIdentidad tipoDocIdentidad, String nroDocIdentidad,
                                     String razonSocial, String direccion) {
        Cliente cliente = obtenerClientePorId(id);

        clienteRepository.findByCodigo(cliente.getCodigo())
                .filter(c -> !c.getId().equals(id) && c.getNroDocIdentidad().equals(nroDocIdentidad))
                .ifPresent(c -> {
                    throw new BusinessException("Ya existe un cliente con el número de documento: " + nroDocIdentidad);
                });

        Cliente actualizado = cliente.actualizar(tipoDocIdentidad, nroDocIdentidad, razonSocial, direccion);
        return clienteRepository.save(actualizado);
    }

    @Override
    public Cliente actualizarEstadoCliente(UUID id, Boolean activo) {
        Cliente cliente = obtenerClientePorId(id);
        Cliente actualizado = cliente.cambiarEstado(activo);
        return clienteRepository.save(actualizado);
    }

    @Override
    public void eliminarCliente(UUID id) {
        if (!clienteRepository.findById(id).isPresent()) {
            throw new ClienteNotFoundException(id);
        }
        clienteRepository.deleteById(id);
    }
}
