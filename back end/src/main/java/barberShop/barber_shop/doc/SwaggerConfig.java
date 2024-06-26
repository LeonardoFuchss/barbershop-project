package barberShop.barber_shop.doc;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class SwaggerConfig {

    private Contact contact() {
        return new Contact(
                "Seu nome",
                "http://www.seusite.com.br",
                "voce@gmail.com"
        );
    }

    private ApiInfoBuilder apiInfoBuilder() { // infos da api
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("Api rest");
        apiInfoBuilder.description("Api´s para requisições http (CRUD)");
        apiInfoBuilder.termsOfServiceUrl("Termo de uso: Open source");
        apiInfoBuilder.contact(this.contact());

        return apiInfoBuilder;
    }

    public Docket detalheApi() { // configurando documentação a partir do pacote do controller e configurando para consumir json

        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.select()
                .apis(RequestHandlerSelectors.basePackage("barberShop.barber_shop.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfoBuilder().build())
                .consumes(new HashSet<String>(Arrays.asList("Application/json")))
                .produces(new HashSet<String>(Arrays.asList("Application/jason")));

        return docket;
    }
}
