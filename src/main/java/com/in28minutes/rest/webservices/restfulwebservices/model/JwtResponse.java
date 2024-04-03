package com.in28minutes.rest.webservices.restfulwebservices.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class JwtResponse {

    private String username;
    private String jwtToken;
}
