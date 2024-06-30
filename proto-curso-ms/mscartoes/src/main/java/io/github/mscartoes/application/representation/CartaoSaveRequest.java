package io.github.mscartoes.application.representation;

import io.github.mscartoes.domain.BandeiraCartao;
import io.github.mscartoes.domain.Cartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoSaveRequest {

    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao toModel() {
        return new Cartao(this.nome, this.bandeira, this.renda, this.limite);
    }
}
