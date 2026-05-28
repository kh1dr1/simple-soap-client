package lt.vikoeif.pi24.simple_soap_client.servlets;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {
    @Bean
    public ServletRegistrationBean<StatusServlet> statusServlet() {
        ServletRegistrationBean<StatusServlet> bean = new ServletRegistrationBean<>();
        bean.setServlet(new StatusServlet());
        bean.addUrlMappings("/status");
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    public ServletRegistrationBean<HtmlServlet> htmlServlet() {
        ServletRegistrationBean<HtmlServlet> bean = new ServletRegistrationBean<>();
        bean.setServlet(new HtmlServlet());
        bean.addUrlMappings("/html");
        return bean;
    }

    @Bean
    public ServletRegistrationBean<HtmlFormServlet> htmlFormServlet() {
        ServletRegistrationBean<HtmlFormServlet> bean = new ServletRegistrationBean<>();
        bean.setServlet(new HtmlFormServlet());
        bean.addUrlMappings("/form", "/submit");
        return bean;
    }
}
