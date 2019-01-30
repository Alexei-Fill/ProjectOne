package com.SpEx7.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class MvcWebApplicationInitalizer implements WebApplicationInitializer {
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[] {AppConfig.class};
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class[] {WebMvcConfig.class};
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[] {"/"};
//    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("com.SpEx7.config");
        servletContext.addListener(new ContextLoaderListener(context));
        FilterRegistration.Dynamic encod =  servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        encod.setInitParameter("encoding", "UTF-8");
        encod.setInitParameter("forceEncoding", "true");
        encod.addMappingForServletNames(null, true, "/*");
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}


//    public void onStartup(ServletContext container) {
//        AnnotationConfigWebApplicationContext context
//                = new AnnotationConfigWebApplicationContext();
//        context.setConfigLocation("com.example.app.config");
//
//        container.addListener(new ContextLoaderListener(context));
//
//        ServletRegistration.Dynamic dispatcher = container
//                .addServlet("dispatcher", new DispatcherServlet(context));
//
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//    }