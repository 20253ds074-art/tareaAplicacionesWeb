package mx.edu.utez.proyecto1C.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaRentaDTO {
    private String nombreCliente;
    private String tipoVehiculo;
    private double importeTotal;
    private String mensaje;
}