package com.example.productosapi.infrastructure.rest.dto;

import com.example.productosapi.domain.model.TipoDocIdentidad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteUpdateRequest {

    @NotNull(message = "El tipo de documento de identidad es obligatorio")
    private TipoDocIdentidad tipoDocIdentidad;

    @NotBlank(message = "El número de documento de identidad es obligatorio")
    @Size(min = 8, max = 20, message = "El número de documento debe tener entre 8 y 20 caracteres")
    private String nroDocIdentidad;

    @NotBlank(message = "La razón social es obligatoria")
    @Size(min = 3, max = 200, message = "La razón social debe tener entre 3 y 200 caracteres")
    private String razonSocial;

    @Size(max = 300, message = "La dirección no puede exceder 300 caracteres")
    private String direccion;
}
