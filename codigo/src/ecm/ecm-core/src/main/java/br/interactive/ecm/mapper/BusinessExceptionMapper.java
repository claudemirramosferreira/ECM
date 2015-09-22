package br.interactive.ecm.mapper;

import br.interactive.ecm.exception.BusinessException;
import br.interactive.ecm.message.BusinessMessage;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Provider para mapear as exceções do tipo
 * {@link BusinessException} no {@link Response}.
 */
@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

    @Inject
    private BusinessMessage messages;

    @Override
    public Response toResponse(BusinessException ex) {
        br.interactive.ecm.response.Response<Void> response = new br.interactive.ecm.response.Response<Void>();
        response.setMessages(messages.getMessages());
        response.getMessages().add(ex.getBusinessMessage());

        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
        builder.type(MediaType.APPLICATION_JSON);
        builder.entity(response);
        return builder.build();
    }

}
