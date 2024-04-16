package spring.mvc.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
public class CustomFreeMarkerConfiguration {

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setExposeRequestAttributes(true);
        resolver.setAllowRequestOverride(true);
        resolver.setExposeSessionAttributes(true);
        resolver.setAllowSessionOverride(true);
        resolver.setCache(true);
        resolver.setPrefix("");
        resolver.setSuffix(".ftlh");
        return resolver;
    }
    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPaths("/","classpath:/templates");
        return freeMarkerConfigurer;
    }
}
