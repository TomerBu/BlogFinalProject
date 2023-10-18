package edu.tomerbu.blogfinalproject.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlogFinalProjectConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
