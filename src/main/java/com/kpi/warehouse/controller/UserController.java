package com.kpi.warehouse.controller;

import com.kpi.warehouse.controller.chain.EmailValidator;
import com.kpi.warehouse.controller.chain.NameValidator;
import com.kpi.warehouse.controller.chain.PhoneValidator;
import com.kpi.warehouse.controller.chain.RoleValidator;
import com.kpi.warehouse.controller.chain.Validator;
import com.kpi.warehouse.dto.user.UserCreateDto;
import com.kpi.warehouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final Validator validator;

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserCreateDto user) {
        if(!validator.validate(user)) {
            return ResponseEntity.badRequest().body("Validation error");
        }
        return ResponseEntity.ok(userService.addUser(user));
    }
}
