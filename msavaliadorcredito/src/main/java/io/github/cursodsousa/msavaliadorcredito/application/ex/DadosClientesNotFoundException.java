package io.github.cursodsousa.msavaliadorcredito.application.ex;

public class DadosClientesNotFoundException extends Exception {
    public DadosClientesNotFoundException() {
        super("Dados do Cliente não encontrados para o cpf encontrado");
    }
}
