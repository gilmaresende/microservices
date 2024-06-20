package io.github.cursodsousa.msavaliadorcredito.application;

import feign.FeignException;
import io.github.cursodsousa.msavaliadorcredito.application.ex.DadosClientesNotFoundException;
import io.github.cursodsousa.msavaliadorcredito.application.ex.ErroComunicacaoMicroservicesException;
import io.github.cursodsousa.msavaliadorcredito.clients.CartoesResourceClient;
import io.github.cursodsousa.msavaliadorcredito.clients.ClientesResourceClient;
import io.github.cursodsousa.msavaliadorcredito.domain.CartaoCliente;
import io.github.cursodsousa.msavaliadorcredito.domain.DadosCliente;
import io.github.cursodsousa.msavaliadorcredito.domain.SituacaoCliente;
import io.github.cursodsousa.msavaliadorcredito.model.Cartao;
import io.github.cursodsousa.msavaliadorcredito.model.CartaoAprovado;
import io.github.cursodsousa.msavaliadorcredito.model.RetornoAvaliacaoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClientesResourceClient clientesClient;

    private final CartoesResourceClient cartoesClient;

    public SituacaoCliente obterSiatuacaoCliente(String cpf) throws DadosClientesNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<DadosCliente> dadosCliente = clientesClient.dadosCliente(cpf);

            ResponseEntity<List<CartaoCliente>> dadosCartoes = cartoesClient.findCartoesByCliente(cpf);

            return SituacaoCliente.builder().cliente(dadosCliente.getBody())
                    .cartoes(dadosCartoes.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClientesNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }

    }

    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda) throws DadosClientesNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<DadosCliente> dadosCliente = clientesClient.dadosCliente(cpf);

            ResponseEntity<List<Cartao>> dadosCartoes = cartoesClient.getCartoesRendaAte(renda);

            List<Cartao> cartaos = dadosCartoes.getBody();

            var cartoesaprovado = cartaos.stream().map(cartao -> {
                CartaoAprovado aprovado = new CartaoAprovado();
                aprovado.setNome(cartao.getNome());
                aprovado.setBandeira(cartao.getBandeira());

                Integer idade = dadosCliente.getBody().getIdade();

                BigDecimal limiteBasico = cartao.getLimitetBasico();
                BigDecimal idadeBd = BigDecimal.valueOf(idade);
                var fator = idadeBd.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                aprovado.setLimiteAprovado(limiteAprovado);
                return aprovado;
            }).toList();

            return new RetornoAvaliacaoCliente(cartoesaprovado);


        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClientesNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }
}
