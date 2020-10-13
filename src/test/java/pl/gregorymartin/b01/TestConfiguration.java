package pl.gregorymartin.b01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
class TestConfiguration {
    @Bean
    @Primary
    @Profile("test")
    DataSource test_dataSource_forRepositories(){
        DriverManagerDataSource result = new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa","");
        result.setDriverClassName("org.h2.Driver");
        return result;
    }
}
