package com.greenfield.springbootmvc.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.greenfield.springbootmvc.domain"})
@EnableJpaRepositories(basePackages = {"com.greenfield.springbootmvc.repositories"})
@EnableTransactionManagement
public class RepositoryConfig {
}
