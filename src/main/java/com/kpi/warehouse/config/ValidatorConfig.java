package com.kpi.warehouse.config;

import com.kpi.warehouse.controller.chain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfig {

    @Bean
    public Validator populateValidator() {
        Validator emailValidator = new EmailValidator();
        Validator nameValidator = new NameValidator();
        Validator phoneValidator = new PhoneValidator();
        Validator roleValidator = new RoleValidator();
        emailValidator.setNextValidator(nameValidator);
        nameValidator.setNextValidator(phoneValidator);
        phoneValidator.setNextValidator(roleValidator);

        return emailValidator;
    }
}
