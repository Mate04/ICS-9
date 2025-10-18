package TDD_CompraEntradas_tp6.demo.controllers;

import TDD_CompraEntradas_tp6.demo.entities.EntradasRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntradasController {

    @PostMapping("/compras")
    public EntradasResponse registrarCompra(@RequestBody EntradasRequest request) {
        double precioTotal = calculadoraPrecios.cuchuflito(request.getEntradas());
        return new CompraResumen(request.getEntradas().size(), precioTotal);
    }

}
