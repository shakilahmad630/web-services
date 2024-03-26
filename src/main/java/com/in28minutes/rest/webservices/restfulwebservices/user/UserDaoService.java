package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static  int userCount =0;
    private static List<User> users = new ArrayList<>();
    static
    {
        users.add(new User(++userCount,"Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount,"Jim", LocalDate.now().minusYears(25)));
        users.add(new User(++userCount,"Eve", LocalDate.now().minusYears(20)));

    }
    public List<User> findAll()
    {
        return  users;
    }

public User findUser(int id)
{
    Predicate<? super User> predicate = user -> user.getId() == id; // Corrected method call and added comparison operator
    return users.stream().filter(predicate).findFirst().orElse(null); // Corrected variable name and added null check
}

    public boolean deleteuserbyid(int id)
    {
        Predicate<? super User> predicate = user -> user.getId() == id; // Corrected method call and added comparison operator
        return users.removeIf(predicate); // Use removeIf method to remove user based on predicate
    }

public User saveUser(User user)
{
    user.setId(++userCount);
   users.add(user);
   return user;
}

}
