package com.example.order.service;

import com.example.order.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserService {
    @GetMapping("/api/v1/users/valid/{authorizationCode}")
    ResponseDto<Boolean> isValidAuthorizationCode(@PathVariable String authorizationCode);
}
