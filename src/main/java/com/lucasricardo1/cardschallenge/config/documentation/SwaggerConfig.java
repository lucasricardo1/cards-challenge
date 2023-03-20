package com.lucasricardo1.cardschallenge.config.documentation;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Value("${spring.application.name}")
	private String applicationName;

	@Value("${spring.application.description:}")
	private String applicationDescription;

	@Value("${spring.application.version:}")
	private String applicationVersion;


	/**
	 * Configuração do docket de inicialização do Swagger
	 *
	 * @return
	 */
	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title(applicationName)
						.description(applicationDescription)
						.version(applicationVersion)
						.license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation()
						.description("SpringShop Wiki Documentation")
						.url("https://springshop.wiki.github.org/docs"));
	}
}