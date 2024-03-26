package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource)
    {
        this.messageSource = messageSource;
    }


    @GetMapping(path = "/helloworld")
    public String helloworld()
    {
        return "Hello World.";
    }

    @GetMapping(path = "/helloworld-bean")
    public HelloWorldBean helloworldbean()
    {
        return new HelloWorldBean("Hello World.") ;
    }

    @GetMapping(path="/helloworld/path-variable/{name}")
    public HelloWorldBean helloworldpathvariable(@PathVariable String name)
    {
        return  new HelloWorldBean(String.format("Hello World, %s",name));
    }
    @GetMapping(path = "/helloworld-internalization")
    public String helloworldInternalization()
    {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Defaule Message",locale);
        //return "Hello World v2.";
    }

    @GetMapping("/hellodata")
    public int helloData()
    {
        return 4528;
    }
}
