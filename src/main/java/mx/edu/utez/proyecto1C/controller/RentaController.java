package mx.edu.utez.proyecto1C.controller;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1C.controller.dto.RentaDTO;
import mx.edu.utez.proyecto1C.controller.dto.RespuestaRentaDTO;
import mx.edu.utez.proyecto1C.service.RentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/renta-vehiculos")
public class RentaController {

    private final RentaService rentaService;

    public RentaController(RentaService rentaService) {
        this.rentaService = rentaService;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<RespuestaRentaDTO> cotizarRenta(@RequestBody @Valid RentaDTO renta) {
        RespuestaRentaDTO respuesta = rentaService.calcularImporteRenta(renta);
        return ResponseEntity.ok(respuesta);
    }
}