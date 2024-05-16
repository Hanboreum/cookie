package com.example.cookie.model;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserDto {

    private String id; //랜덤한 id
    private String name;
    private String password;

}
