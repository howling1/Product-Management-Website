package org.example.listener;

import org.example.pojo.ProductType;
import org.example.service.ProductTypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

// this listener is used for load product types(frequently needed in frontend) info to ServletContext when the app run so that the frontend can get the types info directly without requesting backend again
// @WebListener is an annotation from Servlet not Spring, it will create a Listener in the Tomcat server without registering in the web.xml
@WebListener
public class ProductTypeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // get obejects from spring application context manually because the listener is not in spring application context but only in tomcat web context
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext_*.xml");
        ProductTypeService productTypeService = (ProductTypeService) applicationContext.getBean("ProductTypeServiceImpl");
        List<ProductType> productTypes = productTypeService.getProductTypes();

        servletContextEvent.getServletContext().setAttribute("productTypes", productTypes);
    }

    //not needed
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        return;
    }
}
