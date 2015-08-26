package be.florentbo.register.controller;

import be.florentbo.register.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.Mockito.mock;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = OrderController.class)
public class TestConfiguration {

    @Bean
    public OrderService dumpService() {
        return mock(OrderService.class);
    }

    @Bean
    public ViewResolver viewResolver()
    {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("templates/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }
}
