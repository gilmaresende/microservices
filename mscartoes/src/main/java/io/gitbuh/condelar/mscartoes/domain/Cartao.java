package io.gitbuh.condelar.mscartoes.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private BanderiraCartao banderiraCartao;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    public Cartao(String nome, BanderiraCartao banderiraCartao, BigDecimal renda, BigDecimal limiteBasico) {
        this.nome = nome;
        this.banderiraCartao = banderiraCartao;
        this.renda = renda;
        this.limiteBasico = limiteBasico;
    }
}
