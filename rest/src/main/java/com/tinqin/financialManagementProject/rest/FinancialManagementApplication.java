package com.tinqin.financialManagementProject.rest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.tinqin"})
@EntityScan(basePackages = {"com.tinqin.financialManagementProject.persistence.entity"})
@EnableJpaRepositories(basePackages = {"com.tinqin.financialManagementProject.persistence.repository"})
@EnableFeignClients(basePackages ={"com.tinqin.financialManagementProject.domain"})
@SpringBootApplication
public class FinancialManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancialManagementApplication.class, args);
    }
}
