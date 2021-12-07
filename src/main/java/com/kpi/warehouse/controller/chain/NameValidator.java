package com.kpi.warehouse.controller.chain;

import com.kpi.warehouse.dto.user.UserCreateDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator extends BaseUserValidator {

    public static final Pattern VALID_NAME_REGEX =
            Pattern.compile("^[a-zA-Z]*$", Pattern.CASE_INSENSITIVE);


    @Override
    public boolean validate(UserCreateDto userCreateDto) {
        Matcher firstNameMatcher = VALID_NAME_REGEX.matcher(userCreateDto.getFirstName());
        Matcher secondNameMatcher = VALID_NAME_REGEX.matcher(userCreateDto.getFirstName());
        if (!firstNameMatcher.matches() || !secondNameMatcher.matches()) {
            return false;
        }
        return super.validate(userCreateDto);
    }
}
