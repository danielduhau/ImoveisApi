package com.danielduhau.ImoveisApi.config;

import com.danielduhau.ImoveisApi.model.AppUser;
import com.danielduhau.ImoveisApi.model.Imovel;
import com.danielduhau.ImoveisApi.model.Role;
import com.danielduhau.ImoveisApi.repository.ImovelRepository;
import com.danielduhau.ImoveisApi.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ImovelConfig {

    @Bean
    CommandLineRunner commandLineRunner (
            ImovelRepository imovelRepository, AppUserService appUserService){
        return args -> {

            appUserService.saveRole( new Role(null, "ROLE_USER"));
            appUserService.saveRole( new Role(null, "ROLE_ADMIN"));

            appUserService.saveAppUser(new AppUser(null, "Maria Luzia", "luzia", "1234", new ArrayList<>()));
            appUserService.saveAppUser(new AppUser(null, "Daniel Sampaio", "daniel.duhau@gmail.com", "1234", new ArrayList<>()));

            appUserService.addRoleToAppUser("luzia", "ROLE_USER");
            appUserService.addRoleToAppUser("daniel.duhau@gmail.com", "ROLE_ADMIN");

            Imovel casa1 = new Imovel(
                    "20310360",
                    "Al. circular cuba, n.12 - Jardim das Américas",
                    "Snoop Dog"
            );

            Imovel casa2 = new Imovel(
                    "23010393",
                    "Rua visconde de Porto Alegre,n.13",
                    "snoop dog"
            );

            Imovel casa3 = new Imovel(
                    "9283701293",
                    "Rua ajuricaba n.12",
                    "Lucia campos de oliveira"
            );

            imovelRepository.saveAll(List.of(casa1, casa2, casa3)
            );
        };
    }
}
