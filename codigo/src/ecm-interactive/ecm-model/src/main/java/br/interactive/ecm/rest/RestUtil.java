/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.interactive.ecm.rest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

/**
 *
 * @author robson.ramos
 */
public final class RestUtil {

    private static final String URL_REST_ENVIO_EMAIL = "/rest/enviar";

    public static void enviarEmailRest(Object obj) throws Exception {

        final String segurancaContext = "ehs-email";
        final String hostname = "127.0.0.1";
//                ThreadLocalContextHolder.getInstance().get(ConstantUtils.SERVER_HOST_NAME).toString();
        final String port = "8080";
//                ThreadLocalContextHolder.getInstance().get(ConstantUtils.SERVER_PORT).toString();

        // FIXME URL serviço
        final String fullUrl = "http://" + hostname + ":" + port + "/" + segurancaContext + URL_REST_ENVIO_EMAIL;

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(fullUrl);

        Response response =  target.request().post(Entity.entity(obj, "application/json"));

//        if (response.getStatus() != 200) {
//            throw new Exception("Falha ao enviar e-mail : HTTP error code : " + response.getStatus());
//        }
//        System.out.println(response.readEntity(String.class));
        response.close();
    }

}
