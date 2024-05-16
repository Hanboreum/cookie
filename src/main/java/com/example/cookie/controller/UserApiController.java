package com.example.cookie.controller;

import com.example.cookie.model.UserDto;
import com.example.cookie.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/user") //
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/me") //http://localhost:8080/api/user/me
    public UserDto me (//userdto 반환
            HttpServletRequest httpServletRequest
    ){
        var cookies = httpServletRequest.getCookies();
        if(cookies != null ){
            for( Cookie cookie : cookies){
                log.info("key:{} , value:{}", cookie.getName(), cookie.getValue());
            }
        }
        return  null;
    }
}
