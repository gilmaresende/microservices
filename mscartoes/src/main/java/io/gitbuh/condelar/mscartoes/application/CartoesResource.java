package io.gitbuh.condelar.mscartoes.application;

import io.gitbuh.condelar.mscartoes.application.representation.CartaoSaveRequest;
import io.gitbuh.condelar.mscartoes.application.representation.CartaoesPorClienteResponse;
import io.gitbuh.condelar.mscartoes.domain.Cartao;
import io.gitbuh.condelar.mscartoes.domain.ClienteCartao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartoesResource {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @GetMapping
    public String status() {
        return "<h1>Cartões</h1>";
    }

    @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoSaveRequest request) {
        Cartao cartao = request.toModel();
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda) {
        List<Cartao> list = cartaoService.getCartoesrendaMenirIgual(renda);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartaoesPorClienteResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf) {
        List<CartaoesPorClienteResponse> listResponse = clienteCartaoService.listaCartoesByCpf(cpf).stream().map(m -> new CartaoesPorClienteResponse(m)).toList();
        return ResponseEntity.ok(listResponse);
    }
}
