package com.example.productosapi.infrastructure.rest.controller;

import com.example.productosapi.application.usecase.CreateClienteUseCase;
import com.example.productosapi.application.usecase.DeleteClienteUseCase;
import com.example.productosapi.application.usecase.GetClienteUseCase;
import com.example.productosapi.application.usecase.UpdateClienteUseCase;
import com.example.productosapi.domain.model.Cliente;
import com.example.productosapi.infrastructure.persistence.mapper.ClienteMapper;
import com.example.productosapi.infrastructure.rest.dto.ClienteRequest;
import com.example.productosapi.infrastructure.rest.dto.ClienteResponse;
import com.example.productosapi.infrastructure.rest.dto.ClienteUpdateRequest;
import com.example.productosapi.infrastructure.rest.dto.EstadoUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final CreateClienteUseCase createClienteUseCase;
    private final GetClienteUseCase getClienteUseCase;
    private final UpdateClienteUseCase updateClienteUseCase;
    private final DeleteClienteUseCase deleteClienteUseCase;
    private final ClienteMapper mapper;

    @PostMapping
    public ResponseEntity<ClienteResponse> crearCliente(@Valid @RequestBody ClienteRequest request) {
        Cliente cliente = mapper.toDomain(request);
        Cliente creado = createClienteUseCase.execute(cliente);
        return new ResponseEntity<>(mapper.toResponse(creado), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> obtenerCliente(@PathVariable UUID id) {
        Cliente cliente = getClienteUseCase.executeById(id);
        return ResponseEntity.ok(mapper.toResponse(cliente));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<ClienteResponse> obtenerClientePorCodigo(@PathVariable String codigo) {
        Cliente cliente = getClienteUseCase.executeByCodigo(codigo);
        return ResponseEntity.ok(mapper.toResponse(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> obtenerTodosLosClientes() {
        List<Cliente> clientes = getClienteUseCase.executeAll();
        return ResponseEntity.ok(mapper.toResponseList(clientes));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> actualizarCliente(
            @PathVariable UUID id,
            @Valid @RequestBody ClienteUpdateRequest request) {
        Cliente actualizado = updateClienteUseCase.execute(
                id,
                request.getTipoDocIdentidad(),
                request.getNroDocIdentidad(),
                request.getRazonSocial(),
                request.getDireccion()
        );
        return ResponseEntity.ok(mapper.toResponse(actualizado));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<ClienteResponse> actualizarEstadoCliente(
            @PathVariable UUID id,
            @Valid @RequestBody EstadoUpdateRequest request) {
        Cliente actualizado = updateClienteUseCase.executeEstado(id, request.getActivo());
        return ResponseEntity.ok(mapper.toResponse(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable UUID id) {
        deleteClienteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
