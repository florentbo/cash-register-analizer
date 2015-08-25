package be.florentbo.register.config;

import be.florentbo.register.RegisterApplication;
import be.florentbo.register.repository.RegisterOrderRepository;
import be.florentbo.register.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;

import static be.florentbo.register.repository.Factory.createDay;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = RegisterApplication.class)
public class BusinessConfigurationTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testOrderService() throws Exception {
        assertThat(orderService).isNotNull();

    }

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Test
    public void testExtension() throws Exception {
        assertThat(templateEngine.getDialects()).hasAtLeastOneElementOfType(Java8TimeDialect.class);
    }
}