package be.florentbo.register.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class EmbeddedDataSourceConfig {

    @Bean(destroyMethod="shutdown")
    public DataSource dataSource() {
        return new EmbeddedMysqlDatabaseBuilder().addSqlScript("register_schema.sql").addSqlScript("data.sql").build();
    }
}
