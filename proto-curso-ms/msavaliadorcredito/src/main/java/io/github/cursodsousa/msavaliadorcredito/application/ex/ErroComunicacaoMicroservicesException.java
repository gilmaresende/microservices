package io.github.cursodsousa.msavaliadorcredito.application.ex;

public class ErroComunicacaoMicroservicesException extends Exception {

    Integer status;

    public ErroComunicacaoMicroservicesException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
