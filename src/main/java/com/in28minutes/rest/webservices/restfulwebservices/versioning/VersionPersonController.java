package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionPersonController {

    @GetMapping("/v1/person")
    public Personv1 getFirstVersionofPerson()
    {
        return new Personv1("Bob charlie");
    }
    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionofPerson()
    {
        return new PersonV2( new Name("Bob", "charlie"));
    }
    @GetMapping(path = "/person" ,params="version=1")
    public Personv1 getFirstVersionofPersonRequestParameter()
    {
        return new Personv1( "Bob charlie");
    }
    @GetMapping(path = "/person" ,params="version=2")
    public PersonV2 getSecondVersionofPersonRequestParameter()
    {
        return new PersonV2( new Name("Bob", "charlie"));
    }

    @GetMapping(path = "/person/header" ,headers="X-API-Version=1")
    public Personv1 getFirstVersionofPersonRequestParameterHeader()
    {
        return new Personv1 ( "Bob harlie");
    }
    @GetMapping(path = "/person/header" ,headers="X-API-Version=2")
    public PersonV2 getSecondVersionofPersonRequestParameterHeader()
    {
        return new PersonV2( new Name("Bob", "charlie"));
    }

    @GetMapping(path = "/person/accept" ,produces="application/vnd.company.app-v1+json")
    public Personv1 getFirstVersionofPersonAcceptHeader()
    {
        return new Personv1 ( "Bob harlie");
    }

    @GetMapping(path = "/person/accept" ,produces="application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionofPersonAcceptHeader()
    {
        return new PersonV2( new Name("Bob", "charlie"));
    }
}
