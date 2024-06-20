package io.github.mscartoes.application;


import io.github.mscartoes.application.representation.CartaoSaveRequest;
import io.github.mscartoes.application.representation.CartoesPorClienteResponse;
import io.github.mscartoes.domain.Cartao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartoesResouce {

    private final CartaoService cartaoService;

    private final ClienteCartaoService clienteCartaoService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoSaveRequest requenst) {
        Cartao cartao = requenst.toModel();
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda) {
        return ResponseEntity.ok(cartaoService.getCartaoRendaMenorIgual(renda));
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>> findCartoesByCliente(@RequestParam("cpf") String cpf) {
        return ResponseEntity.ok(clienteCartaoService.findCartoesByCpf(cpf).stream().map(m -> new CartoesPorClienteResponse(m.getCartao().getNome(), m.getCartao().getBandeira().toString(), m.getLimite())).toList());
    }
}
