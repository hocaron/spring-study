package hocaron.basic.beanfind;

import hocaron.basic.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beabDefinitiionNames = ac.getBeanDefinitionNames();
        for (String beabDefinitiionName : beabDefinitiionNames) {
            Object bean = ac.getBean(beabDefinitiionName);
            System.out.println("name = " + beabDefinitiionName + "object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beabDefinitiionNames = ac.getBeanDefinitionNames();
        for (String beabDefinitiionName : beabDefinitiionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beabDefinitiionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beabDefinitiionName);
                System.out.println("name = " + beabDefinitiionName + "object = " + bean);
            }
        }
    }
}
