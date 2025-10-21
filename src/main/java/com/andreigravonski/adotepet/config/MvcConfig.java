package com.andreigravonski.adotepet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Obtém o caminho absoluto para o diretório 'upload-dir'
        Path uploadDir = Paths.get("upload-dir");
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        // Mapeia a URL '/uploaded-images/**' para a pasta física 'upload-dir'
        registry.addResourceHandler("/uploaded-images/**")
                .addResourceLocations("file:/" + uploadPath + "/");
    }
}