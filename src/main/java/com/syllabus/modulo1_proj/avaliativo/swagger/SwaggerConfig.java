package com.syllabus.modulo1_proj.avaliativo.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi turmasApi() {
        return GroupedOpenApi.builder()
                .group("turmas")
                .pathsToMatch("/turmas/**")
                .build();
    }

    @Bean
    public GroupedOpenApi materiasApi() {
        return GroupedOpenApi.builder()
                .group("materias")
                .pathsToMatch("/materias/**")
                .build();
    }

    @Bean
    public GroupedOpenApi docentesApi() {
        return GroupedOpenApi.builder()
                .group("docentes")
                .pathsToMatch("/docentes/**")
                .build();
    }

    @Bean
    public GroupedOpenApi alunosApi() {
        return GroupedOpenApi.builder()
                .group("alunos")
                .pathsToMatch("/alunos/**")
                .build();
    }

    @Bean
    public GroupedOpenApi cursosApi() {
        return GroupedOpenApi.builder()
                .group("cursos")
                .pathsToMatch("/cursos/**")
                .build();
    }

    @Bean
    public GroupedOpenApi loginApi() {
        return GroupedOpenApi.builder()
                .group("login")
                .pathsToMatch("/login/**")
                .build();
    }

    @Bean
    public GroupedOpenApi notasApi() {
        return GroupedOpenApi.builder()
                .group("notas")
                .pathsToMatch("/notas/**")
                .build();
    }

    @Bean
    public GroupedOpenApi cadastroApi() {
        return GroupedOpenApi.builder()
                .group("cadastro")
                .pathsToMatch("/cadastro/**")
                .build();
    }

    @Bean
    public GroupedOpenApi dashboardApi() {
        return GroupedOpenApi.builder()
                .group("dashboard")
                .pathsToMatch("/dashboard/**")
                .build();
    }
}