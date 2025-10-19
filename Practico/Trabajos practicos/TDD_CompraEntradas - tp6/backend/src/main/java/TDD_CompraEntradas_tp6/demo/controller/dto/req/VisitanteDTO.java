package TDD_CompraEntradas_tp6.demo.controller.dto.req;

import TDD_CompraEntradas_tp6.demo.entities.TipoEntrada;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VisitanteDTO {
    //TODO: Chequear esto
    //@Min(0)
    //@Max(100)
    @Schema(description = "edad visitante", example = "18")
    private Integer edadVisitante;
    @Schema(description = "tipo entrada", defaultValue = "GENERAL")
    private TipoEntrada tipoEntrada;

}
