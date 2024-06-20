package io.github.mscartoes.application;

import io.github.mscartoes.domain.ClienteCartao;
import io.github.mscartoes.infra.repositories.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository repository;

    public List<ClienteCartao> findCartoesByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
