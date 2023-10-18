package io.gitbuh.condelar.msclientes.application;

import com.ctc.wstx.shaded.msv_core.util.Uri;
import io.gitbuh.condelar.msclientes.application.representation.ClienteSaveRequest;
import io.gitbuh.condelar.msclientes.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteResource {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteSaveRequest request) {
        Cliente cliente = request.toModel();
        cliente = service.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest().query("cpf={cpf}").buildAndExpand(cliente.getCpf()).toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf) {
        var cliente = service.getByCPF(cpf);
        if (cliente.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cliente);
    }
}
