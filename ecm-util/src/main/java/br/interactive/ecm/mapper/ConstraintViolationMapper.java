package br.interactive.ecm.mapper;

import java.util.Iterator;
import java.util.Set;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.interactive.ecm.message.BusinessMessage;
import br.interactive.ecm.message.ErrorMessage;

/**
 * Provider para mapear as exceções do {@link ConstraintViolationException} no
 * {@link Response}.
 */
@Provider
@Priority(Priorities.USER)
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

    @Inject
    private BusinessMessage messages;

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        addMessages(messages, exception);

        br.interactive.ecm.response.Response<Void> response = new br.interactive.ecm.response.Response<Void>();
        response.setMessages(messages.getMessages());
        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
        builder.type(MediaType.APPLICATION_JSON);
        builder.entity(response);
        return builder.build();
    }

    public static void addMessages(BusinessMessage messages, ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        Iterator<ConstraintViolation<?>> it = constraintViolations.iterator();
        ConstraintViolation<?> field = null;
        while (it.hasNext()) {
            field = it.next();
            messages.add(new ErrorMessage(field.getMessage(), field.getPropertyPath().toString()));
        }
    }

}
