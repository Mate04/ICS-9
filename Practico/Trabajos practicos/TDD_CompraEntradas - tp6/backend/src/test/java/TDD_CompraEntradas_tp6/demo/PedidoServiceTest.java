package TDD_CompraEntradas_tp6.demo;


import TDD_CompraEntradas_tp6.demo.controller.dto.req.ValidarDatoDTO;
import TDD_CompraEntradas_tp6.demo.controller.dto.req.VisitanteDTO;
import TDD_CompraEntradas_tp6.demo.controller.dto.res.PedidoResDTO;
import TDD_CompraEntradas_tp6.demo.controller.dto.res.DetallePedidoDTO;
import TDD_CompraEntradas_tp6.demo.entities.*;
import TDD_CompraEntradas_tp6.demo.repository.PedidoRepository;
import TDD_CompraEntradas_tp6.demo.service.PedidoService;
import TDD_CompraEntradas_tp6.demo.utils.FechaValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    private VisitanteDTO crearVisitante(int edad, TipoEntrada tipoEntrada) {
        VisitanteDTO v = new VisitanteDTO();
        v.setEdadVisitante(edad);
        v.setTipoEntrada(tipoEntrada);
        return v;
    }

    @BeforeEach
    void setup() {
        lenient().when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> {
            Pedido p = invocation.getArgument(0);
            p.setId(1L);
            return p;
        });
        // Mock de save para devolver el pedido con ID simulado
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> {
            Pedido p = invocation.getArgument(0);
            p.setId(1L);
            return p;
        });
    }

    // ✅ Caso 1: Fecha inválida
    @Test
    void testFechaInvalida() {
        try (MockedStatic<FechaValidator> mockedValidator = org.mockito.Mockito.mockStatic(FechaValidator.class)) {
            // Simulamos que la validación devuelve false
            mockedValidator.when(() -> FechaValidator.isValidDate(any())).thenReturn(false);

            ValidarDatoDTO dto = new ValidarDatoDTO(LocalDate.now(),
                    new VisitanteDTO[]{crearVisitante(25, TipoEntrada.REGULAR)});

            assertThrows(IllegalArgumentException.class,
                    () -> pedidoService.validarDatos(dto),
                    "Debe lanzar excepción si la fecha es inválida según FechaValidator");
        }
    }

    // ✅ Caso 2: Fecha pasada
    @Test
    void testFechaPasada() {
        ValidarDatoDTO dto = new ValidarDatoDTO(LocalDate.now().minusDays(1),
                new VisitanteDTO[]{crearVisitante(20, TipoEntrada.REGULAR)});
        assertThrows(IllegalArgumentException.class, () -> pedidoService.validarDatos(dto),
                "No debería permitir fechas pasadas");
    }

    // ✅ Caso 3: Fecha en lunes (no disponible)
    @Test
    void testFechaEnLunes() {
        LocalDate proximoLunes = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        ValidarDatoDTO dto = new ValidarDatoDTO(proximoLunes,
                new VisitanteDTO[]{crearVisitante(30, TipoEntrada.REGULAR)});
        assertThrows(IllegalArgumentException.class, () -> pedidoService.validarDatos(dto));
    }

    // ✅ Caso 4: Fecha 25/11 o 01/01 no disponible
    @Test
    void testFechaNoDisponibleEspeciales() {
        LocalDate[] fechasBloqueadas = {LocalDate.of(2025, 12, 25), LocalDate.of(2025, 1, 1)};
        for (LocalDate fecha : fechasBloqueadas) {
            ValidarDatoDTO dto = new ValidarDatoDTO(fecha,
                    new VisitanteDTO[]{crearVisitante(20, TipoEntrada.REGULAR)});
            assertThrows(IllegalArgumentException.class, () -> pedidoService.validarDatos(dto));
        }
    }

    // ✅ Caso 5: Edad mínima 0 años (no paga)
    @Test
    void testEdadMinimaNoPaga() {
        ValidarDatoDTO dto = new ValidarDatoDTO(LocalDate.now(),
                new VisitanteDTO[]{crearVisitante(0, TipoEntrada.REGULAR)});
        PedidoResDTO res = pedidoService.validarDatos(dto);
        assertEquals(0, res.getImporteTotal());
    }

    // ✅ Caso 6: Edad máxima 100 (válida)
    @Test
    void testEdadMaximaValida() {
        ValidarDatoDTO dto = new ValidarDatoDTO(LocalDate.now(),
                new VisitanteDTO[]{crearVisitante(100, TipoEntrada.VIP)});
        PedidoResDTO res = pedidoService.validarDatos(dto);
        assertTrue(res.getImporteTotal() > 0);
    }

    // ✅ Caso 7: Edad fuera de rango negativo
    @Test
    void testEdadNegativa() {
        ValidarDatoDTO dto = new ValidarDatoDTO(LocalDate.now(),
                new VisitanteDTO[]{crearVisitante(-1, TipoEntrada.REGULAR)});
        assertThrows(IllegalArgumentException.class, () -> pedidoService.validarDatos(dto));
    }

    // ✅ Caso 8: Descuento 50% niños (4-15)
    @Test
    void testDescuentoNinos() {
        ValidarDatoDTO dto = new ValidarDatoDTO(LocalDate.now(),
                new VisitanteDTO[]{crearVisitante(10, TipoEntrada.REGULAR)});
        PedidoResDTO res = pedidoService.validarDatos(dto);
        assertEquals(2500, res.getImporteTotal());
    }

    // ✅ Caso 9: Descuento 50% mayores (>=60)
    @Test
    void testDescuentoMayores() {
        ValidarDatoDTO dto = new ValidarDatoDTO(LocalDate.now(),
                new VisitanteDTO[]{crearVisitante(70, TipoEntrada.VIP)});
        PedidoResDTO res = pedidoService.validarDatos(dto);
        assertEquals(5000, res.getImporteTotal());
    }

    // ✅ Caso 10: Pedido con múltiples visitantes (3 REGULAR + 2 VIP)
    @Test
    void testMultiplesVisitantes() {
        VisitanteDTO[] visitantes = {
                crearVisitante(16, TipoEntrada.REGULAR),
                crearVisitante(15, TipoEntrada.REGULAR),
                crearVisitante(2, TipoEntrada.REGULAR),
                crearVisitante(60, TipoEntrada.VIP),
                crearVisitante(59, TipoEntrada.VIP),
                crearVisitante(4, TipoEntrada.VIP),
                crearVisitante(0, TipoEntrada.VIP),
        };
        ValidarDatoDTO dto = new ValidarDatoDTO(LocalDate.now(), visitantes);

        PedidoResDTO res = pedidoService.validarDatos(dto);
        assertEquals(5000 + 2500 + 0 + 5000 + 10000 + 5000, res.getImporteTotal());
        assertEquals(7, res.getResumen().size());
    }

    // ✅ Caso 11: Máximo 10 entradas válidas
    @Test
    void testMaximoDiezEntradas() {
        VisitanteDTO[] visitantes = new VisitanteDTO[10];
        for (int i = 0; i < 10; i++) {
            visitantes[i] = crearVisitante(25, TipoEntrada.REGULAR);
        }
        ValidarDatoDTO dto = new ValidarDatoDTO(LocalDate.now(), visitantes);
        PedidoResDTO res = pedidoService.validarDatos(dto);
        assertEquals(5000 * 10, res.getImporteTotal());
    }

    // ❌ Caso 12: Más de 10 entradas
    @Test
    void testMasDeDiezEntradas() {
        VisitanteDTO[] visitantes = new VisitanteDTO[11];
        for (int i = 0; i < 11; i++) {
            visitantes[i] = crearVisitante(25, TipoEntrada.REGULAR);
        }
        ValidarDatoDTO dto = new ValidarDatoDTO(LocalDate.now(), visitantes);
        assertThrows(IllegalArgumentException.class, () -> pedidoService.validarDatos(dto));
    }
}
