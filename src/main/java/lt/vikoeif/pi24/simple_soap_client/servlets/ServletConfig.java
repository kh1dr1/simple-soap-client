package lt.vikoeif.pi24.simple_soap_client.servlets;

import lt.vikoeif.pi24.simple_soap_client.endpoint.DealershipEndpointClient;

import org.eclipse.jetty.ee11.servlet.DefaultServlet;
import org.eclipse.jetty.ee11.servlet.ServletContextHandler;
import org.eclipse.jetty.ee11.servlet.ServletHolder;

import org.springframework.boot.jetty.servlet.JettyServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
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

    @Bean
    public ServletRegistrationBean<CreateDealershipServlet> createDealershipServlet(
            DealershipEndpointClient dealershipEndpointClient
    ) {
        CreateDealershipServlet servlet = new CreateDealershipServlet(dealershipEndpointClient);

        // NOTE: URL mapping must match HTML form's action
        return new ServletRegistrationBean<>(servlet, "/post-create-dealership");
    }

    @Bean
    public ServletRegistrationBean<GetDealershipServlet> getDealershipServlet(
        DealershipEndpointClient dealershipEndpointClient
    ) {
        ServletRegistrationBean<GetDealershipServlet> bean = new ServletRegistrationBean<>();
        bean.setServlet(new GetDealershipServlet(dealershipEndpointClient));
        bean.addUrlMappings("/get-dealership");
        return bean;
    }

    @Bean
    public WebServerFactoryCustomizer<JettyServletWebServerFactory> jettyDirectoryListingCustomizer() {
        return factory -> factory.addServerCustomizers(server -> {
            ServletContextHandler context = server.getBean(ServletContextHandler.class);
            if (context == null) {
                context = new ServletContextHandler(ServletContextHandler.SESSIONS);
                server.setHandler(context);
            }

            ServletHolder defaultHolder = new ServletHolder(new DefaultServlet());
            defaultHolder.setInitParameter("dirAllowed", "true");
            defaultHolder.setInitParameter("resourceBase", "./");

            // Map to /files/* instead of root - avoids conflicts with existing pages
            context.addServlet(defaultHolder, "/files/*");
        });
    }
}
