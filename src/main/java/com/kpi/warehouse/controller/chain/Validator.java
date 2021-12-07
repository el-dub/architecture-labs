package com.kpi.warehouse.controller.chain;

import com.kpi.warehouse.dto.user.UserCreateDto;

public interface Validator {

    void setNextValidator(Validator validator);

    boolean validate(UserCreateDto userCreateDto);
}
