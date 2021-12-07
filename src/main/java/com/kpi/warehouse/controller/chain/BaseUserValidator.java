package com.kpi.warehouse.controller.chain;

import com.kpi.warehouse.dto.user.UserCreateDto;

public abstract class BaseUserValidator implements Validator {

    private Validator nextValidator;

    public boolean validate(UserCreateDto userCreateDto) {
        if (nextValidator != null) {
            return nextValidator.validate(userCreateDto);
        }
        return true;
    }

    @Override
    public void setNextValidator(Validator nextValidator) {
        this.nextValidator = nextValidator;
    }
}
