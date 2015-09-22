package br.interactive.ecm.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.interactive.ecm.message.BusinessMessage;
import br.interactive.ecm.message.ErrorMessage;
import br.interactive.ecm.message.Message;
//import br.interactive.ecm.util.EnumConstraints;
import org.hibernate.exception.ConstraintViolationException;

/**
 * Provider para mapear as exceções do {@link EJBException} no {@link Response}.
 */
@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {

    @Inject
    private BusinessMessage messages;

    @Override
    public Response toResponse(EJBException ex) {
        br.interactive.ecm.response.Response<Void> response = new br.interactive.ecm.response.Response<Void>();
        Message msg = null;
        List<String> string = new ArrayList<String>();

        if (ex.getCause().getCause() instanceof EntityExistsException) {
            EntityExistsException eEE = (EntityExistsException) ex.getCause().getCause();
            if (eEE.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException cv = (ConstraintViolationException) eEE.getCause();
                if (cv.getCause() instanceof SQLException) {
                    SQLException sql = (SQLException) cv.getCause();
                    string.add(EJBExceptionMapper.tratarExcecaoBanco(sql));
                    msg = new ErrorMessage(string);
                }
            }
        }

        if (ex.getCause().getCause() instanceof PersistenceException) {
            PersistenceException eEE = (PersistenceException) ex.getCause().getCause();
            if (eEE.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException cv = (ConstraintViolationException) eEE.getCause();
                if (cv.getCause() instanceof SQLException) {
                    SQLException sql = (SQLException) cv.getCause();
                    string.add(EJBExceptionMapper.tratarExcecaoBanco(sql));
                    msg = new ErrorMessage(string);
                }
            }
        }

        if (ex.getCause().getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException cv = (ConstraintViolationException) ex.getCause().getCause();
            if (cv.getCause() instanceof SQLException) {
                SQLException sql = (SQLException) cv.getCause();
                string.add(EJBExceptionMapper.tratarExcecaoBanco(sql));
                msg = new ErrorMessage(string);
            }
        }

        response.setMessages(messages.getMessages());
        response.getMessages().add(msg);
        ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
        builder.type(MediaType.APPLICATION_JSON);
        builder.entity(response);
        return builder.build();
    }

    private static String tratarExcecaoBanco(SQLException b) {
        if (b.getMessage().toLowerCase().contains("fk") || b.getMessage().toLowerCase().contains("_fk") || b.getMessage().toLowerCase().contains("fk_")) {
            return "erro.excluir.registro";
        }

        if (b.getMessage().toLowerCase().contains("ak") || b.getMessage().toLowerCase().contains("_ak") || b.getMessage().toLowerCase().contains("ak_")) {
            return "registro.duplicado";
        }

        return b.getMessage();
    }

}
