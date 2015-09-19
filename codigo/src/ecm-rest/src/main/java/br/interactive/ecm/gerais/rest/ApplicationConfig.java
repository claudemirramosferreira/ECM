/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.interactive.ecm.gerais.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author josimar.alencar
 */
@javax.ws.rs.ApplicationPath("/rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(br.interactive.ecm.gerais.rest.AuthenticatorResource.class);
        resources.add(br.interactive.ecm.gerais.rest.CrossOriginResourceSharingFilter.class);
        resources.add(br.interactive.ecm.gerais.rest.SysVersaoResource.class);
        resources.add(br.interactive.ecm.gerais.rest.TipoDocumentoResource.class);
        resources.add(br.interactive.ecm.model.filtro.AutenticacaoInterceptor.class);
    }

}
