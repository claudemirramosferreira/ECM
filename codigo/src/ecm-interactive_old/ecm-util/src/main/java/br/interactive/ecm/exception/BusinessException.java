package br.interactive.ecm.exception;

import java.io.Serializable;

import javax.ejb.ApplicationException;

import br.interactive.ecm.message.Message;

/**
 * Classe de exceção para regras de negócio.
 */
@ApplicationException(rollback = true)
public class BusinessException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Mensagens que podem ser adicionadas para a exceção.
     */
    private Message businessMessage;

    public BusinessException() {
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(Message message) {
        businessMessage = message;
    }

    public Message getBusinessMessage() {
        return businessMessage;
    }

}
