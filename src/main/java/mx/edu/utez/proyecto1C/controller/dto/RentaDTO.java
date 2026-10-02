package mx.edu.utez.proyecto1C.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentaDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotNull(message = "La edad del conductor es obligatoria")
    @Min(value = 0, message = "La edad no puede ser negativa")
    private Integer edadConductor;

    @NotBlank(message = "El tipo de vehículo es obligatorio")
    private String tipoVehiculo;

    @NotNull(message = "El número de días de renta es obligatorio")
    @Min(value = 1, message = "El número de días debe ser al menos 1")
    private Integer diasRenta;

    @NotNull(message = "Los kilómetros estimados son obligatorios")
    @Min(value = 0, message = "Los kilómetros estimados no pueden ser negativos")
    private Integer kilometrosEstimados;

    @NotNull(message = "La indicación del seguro completo es obligatoria")
    private Boolean seguroCompleto;
}