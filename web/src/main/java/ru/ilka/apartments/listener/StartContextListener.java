package ru.ilka.apartments.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.ilka.apartments.Config;
import ru.ilka.apartments.logic.ApartmentLogic;
import ru.ilka.apartments.util.ContextHolder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ContextHolder contextHolder = new ContextHolder();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        contextHolder.setApplicationContext(applicationContext);
        ApartmentLogic apartmentLogic = applicationContext.getBean(ApartmentLogic.class);
        //ActionFactory actionFactory = applicationContext.getBean(ActionFactory.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
