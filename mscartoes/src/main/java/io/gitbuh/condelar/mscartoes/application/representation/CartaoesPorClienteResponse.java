package io.gitbuh.condelar.mscartoes.application.representation;

import io.gitbuh.condelar.mscartoes.domain.ClienteCartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoesPorClienteResponse {

    private String nome;
    private String bandeira;
    private BigDecimal limite;

    public CartaoesPorClienteResponse() {

    }

    public CartaoesPorClienteResponse(ClienteCartao model) {
        setNome(model.getCartao().getNome());
        setBandeira(model.getCartao().getBanderiraCartao().toString());
        setLimite(model.getLimite());
    }
}
