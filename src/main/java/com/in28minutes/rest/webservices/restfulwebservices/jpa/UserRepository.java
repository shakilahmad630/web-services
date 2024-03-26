package com.in28minutes.rest.webservices.restfulwebservices.jpa;

import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.function.Predicate;



public interface UserRepository extends JpaRepository<User,Long> {

    void deleteById(long id);
}
