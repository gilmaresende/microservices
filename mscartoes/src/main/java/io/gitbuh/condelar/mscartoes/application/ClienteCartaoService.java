package io.gitbuh.condelar.mscartoes.application;

import io.gitbuh.condelar.mscartoes.domain.ClienteCartao;
import io.gitbuh.condelar.mscartoes.infra.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository repository;

    public List<ClienteCartao> listaCartoesByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
