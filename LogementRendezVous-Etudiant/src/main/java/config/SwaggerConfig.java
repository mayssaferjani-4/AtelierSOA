package config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api") // Définit la racine de l'API
public class SwaggerConfig extends Application {
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Logement API")
                        .version("1.0")
                        .description("Documentation Swagger pour le WebService Logement"));
    }
}
