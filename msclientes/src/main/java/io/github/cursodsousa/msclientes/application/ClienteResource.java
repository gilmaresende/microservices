package io.github.cursodsousa.msclientes.application;

import io.github.cursodsousa.msclientes.application.representation.ClienteSabeRequest;
import io.github.cursodsousa.msclientes.domain.Cliente;
import io.github.cursodsousa.msclientes.servico.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("clientes")
//@Slf4j
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping
    public String status() {
        System.out.println("Obtendo o status do microservice de clientes");
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteSabeRequest request) {
        Cliente cliente = request.toModel();
        service.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest().query("cpf={cpf}").buildAndExpand(cliente.getCpf()).toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<Cliente> dadosCliente(@RequestParam("cpf") String cpf) {
        var cliente = service.findByCpf(cpf);
        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente.get());
    }
}
