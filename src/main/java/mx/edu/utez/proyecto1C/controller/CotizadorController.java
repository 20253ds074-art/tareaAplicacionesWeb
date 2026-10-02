package mx.edu.utez.proyecto1C.controller;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1C.controller.dto.PaqueteDTO;
import mx.edu.utez.proyecto1C.controller.dto.RespuestaCotizacionDTO;
import mx.edu.utez.proyecto1C.service.CotizadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/cotizador")
public class CotizadorController {

    private final CotizadorService cotizadorService;

    public CotizadorController(CotizadorService cotizadorService) {
        this.cotizadorService = cotizadorService;
    }

    @PostMapping("/calcular")
    public ResponseEntity<RespuestaCotizacionDTO> calcularEnvio(@RequestBody @Valid PaqueteDTO paquete) {
        RespuestaCotizacionDTO respuesta = cotizadorService.calcularCostoEnvio(paquete);
        return ResponseEntity.ok(respuesta);
    }
}