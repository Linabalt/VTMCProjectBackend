package com.andriusk.project;

import com.andriusk.project.entity.Project;
import com.andriusk.project.enums.ProjectStatus;
import com.andriusk.project.service.ProjectService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableJpaAuditing
public class ProjectListApplication {

	@Bean
	public CommandLineRunner testData(ProjectService service) {
		return (args) -> {
			service.save(new Project("Test project 1", "A test project", ProjectStatus.DONE));
			service.save(new Project("Test project 2", "Another test project", ProjectStatus.IN_PROGRESS));
		};
	}

	@Bean
	public Docket swaggerDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.andriusk"))
				.build();
	}


	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Projectlist documentation")
				.version("0.0.1 SNAPSHOT")
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectListApplication.class, args);
	}



}
