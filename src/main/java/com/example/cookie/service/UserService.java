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
    public String login(LoginRequest loginRequest, HttpServletResponse httpServletResponse){

        var id = loginRequest.getId();
        var pw = loginRequest.getPassword();

        var optionalUser = userRepository.findByName(id); //사용자의 로그인하고자하는 id

        if(optionalUser.isPresent()){
            var userDto = optionalUser.get(); //optionalUser에서 userDto 빼오기

            if(userDto.getPassword().equals(pw)){
                //userDto의 pw 가 넘어온 pw와 동일하다면
                //쿠키에 해당 정보를 저장
                var cookie = new Cookie("authorization-cookie", userDto.getId());
               return userDto.getId(); //로그인 성공시 사용자 아이디 리턴
            }
        }else{
            throw new RuntimeException("User Not Found");
        }
        return null;
    }
}
