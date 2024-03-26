package com.in28minutes.rest.webservices.restfulwebservices.user;

import  static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserResourceController {

    private UserDaoService service;

    public UserResourceController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retriveallusers()
    {
        return service.findAll();
    }

    @GetMapping("/user/{id}")
    public EntityModel<User> retriveuser(@PathVariable int id)
    {
        User user = service.findUser(id);
        if(user == null)

            throw  new RuntimeException("id : "+id);
            EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveallusers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping("/user/{id}")
    public void deleteuser(@PathVariable int id)
    {

        boolean deleted = service.deleteuserbyid(id);
        if (!deleted) {
            throw new RuntimeException("User not found with id : " + id); // Added exception handling for user not found
        }

    }



    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user)
    {
        User saveduser = service.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(saveduser.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }
}
