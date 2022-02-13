package com.example;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class FlywayMultiDBConfiguration {
	@Bean(initMethod = "migrate")
	@FlywayDataSource
	public Flyway adminFlyway(@Qualifier("adminDataSource") DataSource dataSource) {
		Flyway flyway = new Flyway(
				new FluentConfiguration()
						.locations("db/migration/admin")
						// .schemas("test")
						.dataSource(dataSource)
		);

		log.info("Flyway admin username is: {}", flyway.getConfiguration().getUser());
		return flyway;
	}

	@Bean(initMethod = "migrate")
	@FlywayDataSource
	public Flyway siteFlyway(@Qualifier("siteDataSource") DataSource dataSource) {
		Flyway flyway =  new Flyway(
				new FluentConfiguration()
						.locations("db/migration/site")
						// .schemas("test")
						.dataSource(dataSource)
		);

		log.info("Flyway site username is: {}", flyway.getConfiguration().getUser());
		return flyway;
	}
}
