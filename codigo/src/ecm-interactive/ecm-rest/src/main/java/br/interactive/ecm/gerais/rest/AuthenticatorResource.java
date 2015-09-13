/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.interactive.ecm.gerais.rest;

import br.interactive.ecm.gerais.service.UsuarioService;
import br.interactive.ecm.model.dto.gerais.LoginDTO;
import br.interactive.ecm.response.Response;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author robson.ramos
 */
@Path("/authenticator")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class AuthenticatorResource {
    
    @Inject
    private UsuarioService service;
    
    
    @POST
    @Path("login")
    public Response<LoginDTO> login(LoginDTO loginDTO, @Context HttpServletRequest req) {
        return new Response<>(service.login(loginDTO, req));
    }

    @GET
    @Path("getUser")
    public Response<LoginDTO> getUser(@Context HttpServletRequest req) {
        return new Response<>(service.isGetUser(req));
    }

    
}
