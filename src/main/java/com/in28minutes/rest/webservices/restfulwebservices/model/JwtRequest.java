package com.in28minutes.rest.webservices.restfulwebservices.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class JwtRequest {
    private String username;
    private String password;
}
