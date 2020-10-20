package com.kt.jspjpa.gestor.model.databases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryStaticBlockSingleton {

    private static final Logger logger = LoggerFactory.getLogger(EntityManagerFactoryStaticBlockSingleton.class);

    private static EntityManagerFactory entityManagerFactoryStaticBlockSingleton = null;

    static {
        try {
            entityManagerFactoryStaticBlockSingleton = Persistence.createEntityManagerFactory("com/kt/jspjpa/gestor/model");
        } catch (Exception e) {
            logger.error("Exception occurred during creating EntityManagerFactory singleton instance", e);
        }
    }

    private EntityManagerFactoryStaticBlockSingleton() {

    }

    public static EntityManagerFactory getFactory() {
        return entityManagerFactoryStaticBlockSingleton;
    }

    public static void closeFactory() {
        entityManagerFactoryStaticBlockSingleton.close();
    }
}
