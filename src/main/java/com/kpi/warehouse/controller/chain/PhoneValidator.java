package com.kpi.warehouse.controller.chain;

import com.kpi.warehouse.dto.user.UserCreateDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator extends BaseUserValidator {

    public static final Pattern VALID_PHONE_REGEX =
            Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean validate(UserCreateDto userCreateDto) {
        Matcher matcher = VALID_PHONE_REGEX.matcher(userCreateDto.getPhone());
        if (!matcher.matches()) {
            return false;
        }
        return super.validate(userCreateDto);
    }
}
