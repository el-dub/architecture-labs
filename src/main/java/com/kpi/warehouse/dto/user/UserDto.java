package com.kpi.warehouse.dto.user;

import com.kpi.warehouse.model.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String email;
    private String password;
    private Role role;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
}
