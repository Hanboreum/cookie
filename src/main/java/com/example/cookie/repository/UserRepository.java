package com.example.cookie.repository;

import com.example.cookie.model.UserDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
//사용자 초기화
@Service
public class UserRepository {

    private final List<UserDto> userList = new ArrayList<>();

    public Optional<UserDto> findById(String id){
        return userList
                .stream()
                .filter(it->{
                    return it.getId().equals(id);
                }) // 같은 아이디 첫번째 값 리턴
                .findFirst();
    }

   public Optional<UserDto> findByName(String name){
        return userList
                .stream()
                .filter(it -> { //filter에서 name 찾아와 넘어온 name 과 비교
                    return it.getName().equals(name);
                })
                .findFirst();
   }

    @PostConstruct
    public void init(){

        userList.add(
                new UserDto(
                        UUID.randomUUID().toString(), //UUID = 랜덤id 지정
                        "name1",
                        "1234"
                )
        );
        userList.add(
                new UserDto(
                        UUID.randomUUID().toString(), //UUID = 랜덤id 지정
                        "n2me",
                        "1234"
                )
        );
        userList.add(
                new UserDto(
                        UUID.randomUUID().toString(), //UUID = 랜덤id 지정
                        "3ame",
                        "1234"
                )
        );
    }


}
