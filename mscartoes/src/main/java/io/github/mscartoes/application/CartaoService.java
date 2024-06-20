package io.github.mscartoes.application;

import io.github.mscartoes.domain.Cartao;
import io.github.mscartoes.infra.repositories.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository repository;

    @Transactional
    public Cartao save(Cartao cartao) {
        return repository.save(cartao);
    }

    public List<Cartao> getCartaoRendaMenorIgual(Long renda) {
        return repository.findByRendaLessThanEqual(BigDecimal.valueOf(renda));
    }
}
