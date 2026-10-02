package mx.edu.utez.proyecto1C.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaqueteDTO {
   @NotBlank(message = "El código postal es obligatorio")
   private String codigoPostal;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser positivo")
    private Double peso;

    @NotNull(message = "El largo es obligatorio")
    @Positive(message = "El largo debe ser mayor a cero")
    private Double largoCm;

    @NotNull(message = "El ancho es obligatorio")
    @Positive(message = "El peso debe ser mayor a cero")
    private Double anchoCm;

    @NotNull(message = "El alto es obligatorio")
    @Positive(message = "El alto debe ser mayor a cero")
    private Double altoCm;

    @NotBlank(message = "El envio es obligatorio")
    private String tipoEnvio;

    @NotNull(message = "El valor declarado es obligatorio")
    @Min(value = 0, message = "El valor declarado no puede ser negativo")
    private Double valorDeclarado;


}
