package mx.edu.utez.proyecto1C.service;

import mx.edu.utez.proyecto1C.controller.dto.PaqueteDTO;
import mx.edu.utez.proyecto1C.controller.dto.RespuestaCotizacionDTO;
import mx.edu.utez.proyecto1C.exception.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class CotizadorService {

    public RespuestaCotizacionDTO calcularCostoEnvio(PaqueteDTO paquete) {

        if (paquete.getPeso() > 50) {
            throw new BadRequestException("El paquete excede el peso máximo permitido (50 kg).");
        }

        if (paquete.getLargoCm() > 150 || paquete.getAnchoCm() > 150 || paquete.getAltoCm() > 150) {
            throw new BadRequestException("Ninguna dimensión del paquete puede ser superior a 150 cm.");
        }

        double volumen = paquete.getLargoCm() * paquete.getAnchoCm() * paquete.getAltoCm();
        if (volumen > 1000000) {
            throw new BadRequestException("El volumen del paquete excede el límite permitido (1,000,000 cm³).");
        }

        String tipo = paquete.getTipoEnvio().toUpperCase();
        if (!tipo.equals("ESTANDAR") && !tipo.equals("EXPRESS") && !tipo.equals("MISMO_DIA")) {
            throw new BadRequestException("Tipo de envío no válido. Permitidos: ESTANDAR, EXPRESS, MISMO_DIA.");
        }

        double costo = 80.0;

        costo += paquete.getPeso() * 12.0;

        if (volumen > 50000) {
            costo += 100.0;
        }

        if (tipo.equals("EXPRESS")) {
            costo *= 1.40; // Aumento del 40%
        } else if (tipo.equals("MISMO_DIA")) {
            costo *= 1.70; // Aumento del 70%
        }


        if (paquete.getValorDeclarado() > 10000) {
            costo += paquete.getValorDeclarado() * 0.02;
        }

        return new RespuestaCotizacionDTO(
                paquete.getCodigoPostal(),
                Math.round(costo * 100.0) / 100.0,
                "Cotización calculada exitosamente"
        );
    }
}