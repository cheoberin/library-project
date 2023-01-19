package com.javatos.libraryproject.config;

import com.javatos.libraryproject.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {


    private final DbService dbService;

    @Autowired
    public DevConfig(DbService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public void initDB(){
        this.dbService.initDataBaseTest();
    }
}
