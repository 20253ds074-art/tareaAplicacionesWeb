package mx.edu.utez.proyecto1C.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaHospedajeDTO {
    private String nombreHuesped;
    private String tipoHabitacion;
    private double costoTotal;
    private String mensaje;
}