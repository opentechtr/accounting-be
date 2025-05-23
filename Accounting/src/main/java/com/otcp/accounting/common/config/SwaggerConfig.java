package com.otcp.accounting.common.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    /**
     * Configures the OpenAPI specification for the Accounting System.
     *
     * @return the configured OpenAPI object.
     */
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Accounting API")
                        .description("API Endpoint Decoration")
                        .version("v1.0")
                        .contact(newContact())
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Repository")
                        .url("https://github.com/opentechtr/accounting-be"));
    }

    /**
     * Creates a new Contact object for the API documentation.
     *
     * @return the configured Contact object.
     */
    private Contact newContact() {
        Contact contact = new Contact();
        contact.setName("OpenTechTr");
        contact.setUrl("https://www.github.com/opentechtr");
        contact.setEmail("opentechtr@gmail.com");
        return contact;
    }
}