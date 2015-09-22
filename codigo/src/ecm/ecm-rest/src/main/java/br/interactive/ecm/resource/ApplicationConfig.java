/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.interactive.ecm.resource;

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
        resources.add(br.interactive.ecm.mapper.BusinessExceptionMapper.class);
        resources.add(br.interactive.ecm.mapper.ConstraintViolationMapper.class);
        resources.add(br.interactive.ecm.mapper.EJBExceptionMapper.class);
        resources.add(br.interactive.ecm.model.filtro.AutenticacaoInterceptor.class);
        resources.add(br.interactive.ecm.resource.ApplicationCrossOriginResourceSharingFilter.class);
        resources.add(br.interactive.ecm.resource.gerais.TipoDocumentoResource.class);
        resources.add(br.interactive.ecm.resource.seguranca.MenuResource.class);
        resources.add(br.interactive.ecm.resource.seguranca.UsuarioResource.class);
    }

}
