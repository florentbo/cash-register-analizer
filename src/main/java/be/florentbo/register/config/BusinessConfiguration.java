package be.florentbo.register.config;

import be.florentbo.register.service.DumpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessConfiguration {
    @Bean
    public DumpService dumpService(){
        return new DumpService();
    }
}
