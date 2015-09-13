package br.interactive.ecm.message;

import java.io.Serializable;

public interface Message extends Serializable {

    Type getType();

    String getTarget();

    String getDescription();

    /**
     * Tipos de mensagens tratadas.
     */
    public enum Type {

        /**
         * Aviso.
         */
        WARN,
        /**
         * Falha.
         */
        ERROR,
        /**
         * Sucesso e noticias em geral.
         */
        INFO,
        /**
         * Vazio
         */
        EMPTY

    }

}
