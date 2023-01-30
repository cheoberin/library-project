package com.atos.library.libraryregistry.config;

import com.atos.library.libraryregistry.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {


    private final DbService dbService;

    @Autowired
    public DevConfig(DbService dbService){
        this.dbService = dbService;
    }

  //  @Bean
   // public void initDB(){
     //   this.dbService.initDataBaseTest();
   // }

}
