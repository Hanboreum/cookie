package com.example.cookie.service;

import com.example.cookie.model.LoginRequest;
import com.example.cookie.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    //login logic
    public void login(LoginRequest loginRequest, HttpServletResponse httpServletResponse){

        var id = loginRequest.getId();
        var pw = loginRequest.getPassword();

        var optionalUser = userRepository.findByName(id); //사용자의 로그인하고자하는 id

        if(optionalUser.isPresent()){
            var userDto = optionalUser.get(); //optionalUser에서 userDto 빼오기

            if(userDto.getPassword().equals(pw)){
                //userDto의 pw 가 넘어온 pw와 동일하다면
                //쿠키에 해당 정보를 저장
                var cookie = new Cookie("authorization-cookie", userDto.getId());
                cookie.setDomain("localhost"); //도메인 지정
                cookie.setPath("/");// root 지정
                cookie.setMaxAge(-1);// 연결된 동안만 사용

                httpServletResponse.addCookie(cookie); // http server response에 쿠키 추가
            }
        }else{
            throw new RuntimeException("User Not Found");
        }
    }
}
