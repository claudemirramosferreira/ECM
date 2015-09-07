package br.interactive.ecm.model.filtro;

import javax.ejb.EJBException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Provider para mapear as exceções do {@link EJBException} no {@link Response}.
 */
@Provider
public class AutenticacaoInterceptor implements ContainerRequestFilter {

    @Context
    private HttpServletRequest request;

    @Inject
    private SessaoService sessaoFacade;

    @Context
    private ResourceInfo resourceInfo;

    private static final Response ACCESS_UNAUTHORIZED = Response.status(Response.Status.UNAUTHORIZED).entity(false).encoding(MediaType.APPLICATION_JSON).build();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get method invoked.
        Method methodInvoked = resourceInfo.getResourceMethod();

        if (methodInvoked.isAnnotationPresent(Path.class)) {
            Path p = methodInvoked.getAnnotation(Path.class);
            Produces prod = methodInvoked.getAnnotation(Produces.class);
            if (!p.value().equals("login")) {
                if (prod != null && !prod.value()[0].equals("application/octet-stream")) {
                    if (!sessaoFacade.isConnected(request)) {
                        requestContext.abortWith(ACCESS_UNAUTHORIZED);
                    }
                }
            }
        }
    }
}
