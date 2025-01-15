package com.amazon.sample.orders.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(
  prefix = "retail.orders.persistence",
  name = "provider",
  havingValue = "postgres"
)
@EnableConfigurationProperties(DatabaseProperties.class)
public class DataSourceConfig {

  @Autowired
  private DatabaseProperties dbProps;

  @Bean
  public DataSource dataSource() {
    HikariConfig config = new HikariConfig();

    String jdbcUrl = String.format(
      "jdbc:postgresql://%s/%s",
      dbProps.getEndpoint(),
      dbProps.getName()
    );

    config.setJdbcUrl(jdbcUrl);
    config.setUsername(dbProps.getUsername());
    config.setPassword(dbProps.getPassword());

    return new HikariDataSource(config);
  }
}
