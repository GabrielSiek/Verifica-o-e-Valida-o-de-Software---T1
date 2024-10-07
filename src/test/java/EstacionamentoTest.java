import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;

import org.example.Estacionamento;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EstacionamentoTest {

    @ParameterizedTest
    @CsvSource({
            "2024-10-01T08:00, 2024-10-01T08:10, false, 0.0",
            "2024-10-01T08:00, 2024-10-01T09:00, false, 5.90",
            "2024-10-01T08:00, 2024-10-01T09:01, false, 8.40",
            "2024-10-01T08:00, 2024-10-01T09:30, true, 4.20",
            "2024-10-01T22:00, 2024-10-02T08:10, false, 50.00",
            "2024-10-01T22:00, 2024-10-02T08:10, true, 25.00",
            "2024-10-01T22:00, 2024-10-03T08:10, false, 100.00"
    })
    void testCalculoTarifa(LocalDateTime entrada, LocalDateTime saida, boolean clienteVIP, double esperado) {
        assertEquals(esperado, Estacionamento.calcularTarifa(entrada, saida, clienteVIP));
    }
}
