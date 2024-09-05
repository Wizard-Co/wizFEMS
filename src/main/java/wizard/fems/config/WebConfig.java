package wizard.fems.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private String webPath = "/image/**";
    private String localPath = "file:///D:/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(webPath)
                .addResourceLocations(localPath)
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver((new VersionResourceResolver()).addContentVersionStrategy("/**"));
    }
}
