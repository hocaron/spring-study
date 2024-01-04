package com.spring.boilerplate.util;

import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Map;
import java.util.Objects;

public class EntityManagerUtils {

    public static String getEntityManagerName() {
        EntityManager entityManager = findProxyEntityManager();

        if (entityManager != null) {
            EntityManagerFactory entityManagerFactory = entityManager.getEntityManagerFactory();
            return entityManagerFactory.unwrap(javax.persistence.EntityManagerFactory.class)
                    .getProperties().get("hibernate.ejb.persistenceUnitName").toString();
        }

        return "";
    }

    private static EntityManager findProxyEntityManager() {
        Map<Object, Object> resourceMap = TransactionSynchronizationManager.getResourceMap();

        for (Object key : resourceMap.keySet()) {
            if (isProxy(key)) {
                EntityManagerHolder entityManagerHolder = (EntityManagerHolder) resourceMap.get(key);
                if (entityManagerHolder != null) {
                    return entityManagerHolder.getEntityManager();
                }
            }
        }

        return null;
    }

    private static boolean isProxy(Object obj) {
        return Objects.nonNull(obj) && obj.getClass().getName().startsWith("com.sun.proxy.$Proxy");
    }
}
