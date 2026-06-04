package com.example.studio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry r) {
        Path root = Paths.get("uploads").toAbsolutePath();
        r.addResourceHandler("/uploads/previews/**")
         .addResourceLocations("file:" + root.resolve("previews").toString() + "/");
        // Full-res NOT publicly mapped
    }
}
