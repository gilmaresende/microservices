package io.github.cursodsousa.msclientes.application.representation;

import io.github.cursodsousa.msclientes.domain.Cliente;
import lombok.Data;

@Data
public class ClienteSabeRequest {

    private String cpf;
    private String nome;
    private Integer idade;

    public Cliente toModel() {
        return new Cliente(this.cpf, this.nome, this.idade);
    }
}
