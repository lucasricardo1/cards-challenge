package com.lucasricardo1.cardschallenge.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuração do Datasource postgresql para instancia da api de bureaus
 */
@Profile("!test")
@Configuration
@EnableTransactionManagement
@ConfigurationProperties(prefix = "datasource.postgresql")
@EnableJpaRepositories(//
		basePackages = "com.lucasricardo1.cardschallenge.db.repositories", //
		entityManagerFactoryRef = "postgresql-em", //
		transactionManagerRef = "postgresql-tm")
@EnableJpaAuditing
public class PostgreSqlConfig extends HikariConfig {

	/**
	 * Factory para criação do Data Source
	 *
	 * @return
	 */
	@Primary
	@Bean(name = "postgresql-ds")
	public DataSource postgresDataSourceFactory() {

		return new HikariDataSource(this);
	}

	/**
	 * Factory para criação do Entity Manager
	 *
	 * @param builder
	 * @return
	 */
	@PersistenceContext(unitName = "cards-challenge")
	@Bean(name = "postgresql-em")
	public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
			EntityManagerFactoryBuilder builder) {

		return builder.dataSource(postgresDataSourceFactory()).persistenceUnit("cards-challenge").properties(jpaProperties())
				.packages("com.lucasricardo1.cardschallenge.db.entities").build();
	}

	/**
	 * Factory para criação do Transaction Manager
	 *
	 * @param em
	 * @return
	 */
	@Bean(name = "postgresql-tm")
	public PlatformTransactionManager postgresTransactionManagerFactory(
			@Qualifier("postgresql-em") EntityManagerFactory em) {

		return new JpaTransactionManager(em);
	}

	/**
	 * Propriedades do Persistence Unity
	 *
	 * @return
	 */
	private Map<String, Object> jpaProperties() {

		Map<String, Object> props = new HashMap<>();
		props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		return props;
	}
}
