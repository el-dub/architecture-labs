package com.kpi.warehouse.controller.chain;

import com.kpi.warehouse.dto.user.UserCreateDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator extends BaseUserValidator {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^(.+)@(\\S+)$", Pattern.CASE_INSENSITIVE);


    @Override
    public boolean validate(UserCreateDto userCreateDto) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(userCreateDto.getEmail());
        if (!matcher.matches()) {
            return false;
        }
        return super.validate(userCreateDto);
    }
}
