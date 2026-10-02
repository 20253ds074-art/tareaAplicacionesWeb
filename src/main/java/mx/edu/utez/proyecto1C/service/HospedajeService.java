package mx.edu.utez.proyecto1C.service;

import mx.edu.utez.proyecto1C.controller.dto.HospedajeDTO;
import mx.edu.utez.proyecto1C.controller.dto.RespuestaHospedajeDTO;
import mx.edu.utez.proyecto1C.exception.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class HospedajeService {

    public RespuestaHospedajeDTO calcularCostoHospedaje(HospedajeDTO hospedaje) {

        String habitacion = hospedaje.getTipoHabitacion().toUpperCase();
        String temporada = hospedaje.getTemporada().toUpperCase();
        int noches = hospedaje.getNoches();
        int huespedes = hospedaje.getHuespedes();

        if (noches > 30) {
            throw new BadRequestException("El número de noches no puede superar los 30 días.");
        }

        double costoPorNoche;
        int capacidadMaxima;

        switch (habitacion) {
            case "INDIVIDUAL":
                costoPorNoche = 700.0;
                capacidadMaxima = 1;
                break;
            case "DOBLE":
                costoPorNoche = 1100.0;
                capacidadMaxima = 2;
                break;
            case "SUITE":
                costoPorNoche = 1800.0;
                capacidadMaxima = 4;
                break;
            default:
                throw new BadRequestException("Tipo de habitación no válido. Permitidos: INDIVIDUAL, DOBLE, SUITE.");
        }

        if (huespedes > capacidadMaxima) {
            throw new BadRequestException("El número de huéspedes (" + huespedes +
                    ") supera la capacidad de la habitación " + habitacion + " (Máx. " + capacidadMaxima + " persona(s)).");
        }

        if (!temporada.equals("BAJA") && !temporada.equals("REGULAR") && !temporada.equals("ALTA")) {
            throw new BadRequestException("Temporada no válida. Permitidas: BAJA, REGULAR, ALTA.");
        }


        double costoHospedajeBase = costoPorNoche * noches;

        double costoHospedajeAjustado = costoHospedajeBase;
        if (temporada.equals("BAJA")) {
            costoHospedajeAjustado *= 0.90;
        } else if (temporada.equals("ALTA")) {
            costoHospedajeAjustado *= 1.25;
        }

        if (noches >= 7) {
            costoHospedajeAjustado *= 0.92;
        }

        double costoDesayuno = 0.0;
        if (Boolean.TRUE.equals(hospedaje.getIncluyeDesayuno())) {
            costoDesayuno = huespedes * noches * 150.0;
        }

        double costoEstacionamiento = 0.0;
        if (Boolean.TRUE.equals(hospedaje.getIncluyeEstacionamiento())) {
            costoEstacionamiento = noches * 100.0;
        }

        double subtotal = costoHospedajeAjustado + costoDesayuno + costoEstacionamiento;

        double impuestoHospedaje = subtotal * 0.04;

        double total = subtotal + impuestoHospedaje;

        return new RespuestaHospedajeDTO(
                hospedaje.getNombreHuesped(),
                habitacion,
                Math.round(total * 100.0) / 100.0, // Redondeo a 2 decimales
                "Cotización de hospedaje calculada exitosamente"
        );
    }
}