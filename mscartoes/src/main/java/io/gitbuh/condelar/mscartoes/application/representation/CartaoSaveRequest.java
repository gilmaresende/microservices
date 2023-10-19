package io.gitbuh.condelar.mscartoes.application.representation;

import io.gitbuh.condelar.mscartoes.domain.BanderiraCartao;
import io.gitbuh.condelar.mscartoes.domain.Cartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoSaveRequest {

    private String nome;
    private BanderiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao toModel(){
        return new Cartao(nome, bandeira, renda, limite);
    }
}
