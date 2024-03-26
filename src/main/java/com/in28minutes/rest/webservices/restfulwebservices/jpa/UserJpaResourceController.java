package com.in28minutes.rest.webservices.restfulwebservices.jpa;

import com.in28minutes.rest.webservices.restfulwebservices.user.Post;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserDaoService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResourceController {

    private UserRepository repository;

    private UserDaoService service;



    public UserJpaResourceController(UserDaoService service,UserRepository repository) {
        this.service = service;
        this.repository=repository;
    }

    @GetMapping("/jpa/users")
    public List<User> retriveallusers()
    {
        return repository.findAll();
    }

    @GetMapping("/jpa/user/{id}")
    public EntityModel<User> retriveuser(@PathVariable int id)
    {
        Optional<User> user = repository.findById((long) id);
        if(user.isEmpty())

            throw  new RuntimeException("id : "+id);
            EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveallusers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping("/jpa/user/{id}")
    public void deleteuser(@PathVariable long id) {
        repository.deleteById(id);
    }

    @GetMapping ("/jpa/user/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable long id) {
        Optional<User> user = repository.findById((long) id);
        if(user.isEmpty())

            throw  new RuntimeException("id : "+id);
        return user.get().getPosts();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user)
    {
        User saveduser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(saveduser.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }
}
