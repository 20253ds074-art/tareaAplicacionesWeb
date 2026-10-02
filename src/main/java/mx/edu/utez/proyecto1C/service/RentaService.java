package mx.edu.utez.proyecto1C.service;

import mx.edu.utez.proyecto1C.controller.dto.RentaDTO;
import mx.edu.utez.proyecto1C.controller.dto.RespuestaRentaDTO;
import mx.edu.utez.proyecto1C.exception.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class RentaService {

    public RespuestaRentaDTO calcularImporteRenta(RentaDTO renta) {

        int edad = renta.getEdadConductor();
        int dias = renta.getDiasRenta();
        int kmsEstimados = renta.getKilometrosEstimados();
        String tipoVehiculo = renta.getTipoVehiculo().toUpperCase();

        if (edad < 18) {
            throw new BadRequestException("No se aceptan rentas para conductores menores de 18 años.");
        }

        if (dias > 30) {
            throw new BadRequestException("La renta no puede superar los 30 días.");
        }

        if (kmsEstimados > 5000) {
            throw new BadRequestException("Los kilómetros estimados no pueden superar los 5,000 km.");
        }

        if (tipoVehiculo.equals("CAMIONETA") && edad < 25) {
            throw new BadRequestException("Para solicitar una CAMIONETA el conductor debe tener al menos 25 años.");
        }

        double costoDiario;
        switch (tipoVehiculo) {
            case "COMPACTO":
                costoDiario = 550.0;
                break;
            case "SEDAN":
                costoDiario = 700.0;
                break;
            case "SUV":
                costoDiario = 950.0;
                break;
            case "CAMIONETA":
                costoDiario = 1200.0;
                break;
            default:
                throw new BadRequestException("Tipo de vehículo no válido. Permitidos: COMPACTO, SEDAN, SUV, CAMIONETA.");
        }


        double costoRentaBase = costoDiario * dias;

        double costoRentaFinal = costoRentaBase;
        if (dias >= 7) {
            costoRentaFinal = costoRentaBase * 0.90;
        }

        int kmsIncluidos = dias * 100;
        double cargoKmsAdicionales = 0.0;
        if (kmsEstimados > kmsIncluidos) {
            int kmsExtra = kmsEstimados - kmsIncluidos;
            cargoKmsAdicionales = kmsExtra * 4.0;
        }

        // 4. Cargo por edad (18 a 24 años): 15% sobre la suma del costo de la renta (sin descuento previo) y km adicionales
        double cargoEdad = 0.0;
        if (edad >= 18 && edad <= 24) {
            cargoEdad = (costoRentaBase + cargoKmsAdicionales) * 0.15;
        }

        // 5. Seguro completo: $180 por cada día de renta
        double cargoSeguro = 0.0;
        if (Boolean.TRUE.equals(renta.getSeguroCompleto())) {
            cargoSeguro = 180.0 * dias;
        }

        // Importe Total Acumulado
        double importeTotal = costoRentaFinal + cargoKmsAdicionales + cargoEdad + cargoSeguro;

        return new RespuestaRentaDTO(
                renta.getNombreCliente(),
                tipoVehiculo,
                Math.round(importeTotal * 100.0) / 100.0, // Redondeo a 2 decimales
                "Cotización de renta calculada exitosamente"
        );
    }
}