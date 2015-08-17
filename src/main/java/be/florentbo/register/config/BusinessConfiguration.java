package be.florentbo.register.config;

import be.florentbo.register.repository.RegisterOrderRepository;
import be.florentbo.register.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.annotation.PostConstruct;

@Configuration
public class BusinessConfiguration {

    @Autowired
    private RegisterOrderRepository repository;

    @Bean
    public OrderService dumpService(){
        return new OrderService(repository);
    }

    @Autowired
    private SpringTemplateEngine templateEngine;

    @PostConstruct
    public void extension() {
        templateEngine.addDialect(new Java8TimeDialect());
    }
}
