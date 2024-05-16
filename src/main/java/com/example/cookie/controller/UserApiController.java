package com.example.cookie.controller;

import com.example.cookie.model.UserDto;
import com.example.cookie.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/user") //
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/me") //http://localhost:8080/api/user/me
    public UserDto me(//userdto 반환
                      HttpServletRequest httpServletRequest,
                      @CookieValue(name = "authorization-cookie", required = false) //authorization-cookie 이름을 가진 쿠키를 맵핑
                      String authorizationCookie
    ) {
        log.info("authorization cookie: {}", authorizationCookie);

        //유저 찾기
        var optionalUserDto = userRepository.findById(authorizationCookie);
        return optionalUserDto.get(); //유저 없으면 null, 있으면 user dto값

        /*var cookies = httpServletRequest.getCookies();
        if(cookies != null ){
            for( Cookie cookie : cookies){
                log.info("key:{} , value:{}", cookie.getName(), cookie.getValue());
            }
        }*/
        // return  null;
    }


    @GetMapping("/me2") //http://localhost:8080/api/user/me
    public UserDto me2(//userdto 반환
                       @RequestHeader(name = "authorization", required = false) //필수 값 아님
                       //헤더에 authorizationHeader 가 있으면 해당 값을 가지고 findById에서 리턴
                       String authorizationHeader
    ) {
        log.info("authorizationHeader : {}", authorizationHeader);

        //유저 찾기
        var optionalUserDto = userRepository.findById(authorizationHeader);
        return optionalUserDto.get(); //유저 없으면 null, 있으면 user dto값

    }
}
