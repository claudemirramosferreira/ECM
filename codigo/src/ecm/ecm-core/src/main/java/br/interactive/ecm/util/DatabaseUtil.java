/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2015, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package br.interactive.ecm.util;

import java.util.Map;

import javax.persistence.EntityManager;

/**
 * @author josimar.alencar
 *
 */
public class DatabaseUtil {

    /**
     * Enum dos tipos de dados
     */
    public static enum DatabaseType {

        TYPE_ORACLE("Oracle"), TYPE_SQL_SERVER("Microsoft SQL Server"), TYPE_POSTGRESQL("PostgreSQL");

        /** Pattern. */
        private final String pattern;

        private DatabaseType(String pattern) {
            this.pattern = pattern;
        }

        public String getPattern() {
            return pattern;
        }

    }

    /**
     * Construtor
     */
    public DatabaseUtil() {
    }

    /**
     * Metodo que obtem as propriedades do persistence
     * 
     * @param entityManager
     * @return Map<String, Object>
     */
    private static Map<String, Object> getDataBase(EntityManager entityManager) {
        return entityManager.getEntityManagerFactory().getProperties();
    }

    /**
     * Metodo para retornar o base de dados
     * 
     * @return DatabaseType
     */
    public static DatabaseType getDatabaseType(EntityManager entityManager) {

        String dialect = getDataBase(entityManager).get("hibernate.dialect").toString().toLowerCase();

        if (dialect.contains("sqlserver")) {
            return DatabaseType.TYPE_SQL_SERVER;
        } else if (dialect.contains("postgres")) {
            return DatabaseType.TYPE_POSTGRESQL;
        } else
            return DatabaseType.TYPE_ORACLE;

    }

    /**
     * Metodo que retorna o schema que esta sendo usado
     * 
     * @return String
     */
    public static String getDefaultSchema(EntityManager entityManager) {
        return getDataBase(entityManager).get("hibernate.default_schema").toString();
    }

}