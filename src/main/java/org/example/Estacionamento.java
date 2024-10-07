package org.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Estacionamento {
    private static final double TARIFA_FIXA_UMA_HORA = 5.90;
    private static final double TARIFA_ADICIONAL_POR_HORA = 2.50;
    private static final double TARIFA_PERNOITE = 50.00;
    private static final LocalTime HORARIO_ABERTURA = LocalTime.of(8, 0);
    private static final LocalTime HORARIO_FECHAMENTO = LocalTime.of(2, 0);
    private static final Duration CORTESIA_MINUTOS = Duration.ofMinutes(15);

    public static double calcularTarifa(LocalDateTime entrada, LocalDateTime saida, boolean clienteVIP) {
        Duration duracao = Duration.between(entrada, saida);
        if (duracao.compareTo(CORTESIA_MINUTOS) <= 0) {
            return 0.0; // cortesia
        }

        long horas = duracao.toHours();
        boolean pernoite = saida.toLocalTime().isBefore(HORARIO_ABERTURA);

        double tarifa = 0.0;

        if (pernoite) {
            long diasPernoite = Duration.between(entrada.toLocalDate().atStartOfDay(), saida.toLocalDate().atStartOfDay()).toDays();
            tarifa = TARIFA_PERNOITE * diasPernoite;
        } else {
            if (horas <= 1) {
                tarifa = TARIFA_FIXA_UMA_HORA;
            } else {
                tarifa = TARIFA_FIXA_UMA_HORA + (horas - 1) * TARIFA_ADICIONAL_POR_HORA;
            }
        }

        if (clienteVIP) {
            tarifa *= 0.5; // 50% de desconto para VIP
        }

        return tarifa;
    }
}
