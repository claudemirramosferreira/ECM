/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.interactive.ecm.response;

import br.interactive.ecm.message.Message;
import java.util.List;

public class Response<E> {

    private E element;
    private List<Message> messages;

    public Response() {
    }

    public Response(E element) {
        this.element = element;
    }

    public Response(List<Message> messages) {
        this.messages = messages;
    }

    public Response(E element, List<Message> messages) {
        this.element = element;
        this.messages = messages;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}
