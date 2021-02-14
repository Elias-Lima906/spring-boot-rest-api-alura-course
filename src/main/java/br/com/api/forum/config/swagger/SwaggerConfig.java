package br.com.api.forum.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.api.forum.modelo.Usuario;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket forumAPI() {

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("br.com.api.forum"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.apiInfo(metaData())
				.ignoredParameterTypes(Usuario.class)
				.globalOperationParameters(
						Arrays.asList(new ParameterBuilder()
								.name("Authorization")
								.description("Header para Token JWT")
								.modelRef(new ModelRef("string"))
								.parameterType("header").required(false)
								.build()));
	}
	
	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Forum Alura")
				.description("API que simula um forum de dúvidas da alura, completa com segurança, autenticação e documentação!")
				.version("1.0.0").license("Mit License")
				.build();
	}

}