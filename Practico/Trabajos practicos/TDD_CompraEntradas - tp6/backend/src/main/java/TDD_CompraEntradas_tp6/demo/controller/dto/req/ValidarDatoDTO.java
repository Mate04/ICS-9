package TDD_CompraEntradas_tp6.demo.controller.dto.req;

import TDD_CompraEntradas_tp6.demo.entities.TipoEntrada;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidarDatoDTO {
    @NotNull(message = "La fecha de visita no puede ser nula")
    @Schema(description = "Fecha de la visita", example = "2025-10-19")
    private Date fechaVisita;

    @NotEmpty(message = "Debe enviar al menos un visitante")
    @Valid
    @Schema(description = "Lista de visitantes")
    private VisitanteDTO[] visitantes;
}

