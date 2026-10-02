package mx.edu.utez.proyecto1C.controller;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1C.controller.dto.HospedajeDTO;
import mx.edu.utez.proyecto1C.controller.dto.RespuestaHospedajeDTO;
import mx.edu.utez.proyecto1C.service.HospedajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/cotizador-hospedaje")
public class HospedajeController {

    private final HospedajeService hospedajeService;

    public HospedajeController(HospedajeService hospedajeService) {
        this.hospedajeService = hospedajeService;
    }

    @PostMapping("/calcular")
    public ResponseEntity<RespuestaHospedajeDTO> calcularHospedaje(@RequestBody @Valid HospedajeDTO hospedaje) {
        RespuestaHospedajeDTO respuesta = hospedajeService.calcularCostoHospedaje(hospedaje);
        return ResponseEntity.ok(respuesta);
    }
}