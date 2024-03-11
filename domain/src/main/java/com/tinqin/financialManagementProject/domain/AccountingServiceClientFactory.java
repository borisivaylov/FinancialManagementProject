package com.tinqin.financialManagementProject.domain;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqin.accountingproject.restExport.AccountingRestExport;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AccountingServiceClientFactory {

    @Bean
    public AccountingRestExport getAccountingRestExport(){

        {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            return Feign.builder()
                    .encoder(new JacksonEncoder(objectMapper))
                    .decoder(new JacksonDecoder(objectMapper))
                    .target(AccountingRestExport.class, "http://localhost:8088");
        }

    }
}
