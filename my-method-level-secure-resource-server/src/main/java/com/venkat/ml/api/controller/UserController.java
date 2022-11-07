package com.venkat.ml.api.controller;

import com.venkat.ml.api.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Environment environment;

    @GetMapping("/greetings")
    public String greetins(){
        System.out.println("Port number: " + environment.getProperty("local.server.port"));
        return "Hello, Venkatram R. Veerareddy";
    }

    @GetMapping("/token")
    public Map<String, Object> getToken(@AuthenticationPrincipal Jwt jwt){
        System.out.println("Port number: " + environment.getProperty("local.server.port"));
        return Collections.singletonMap("principal", jwt);
    }

    @PreAuthorize("hasAuthority('ROLE_developer')")
    //@PreAuthorize("hasAuthority('ROLE_developer') or #id == #jwt.subject")
    //@Secured("ROLE_developer")
    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        System.out.println("Port number: " + environment.getProperty("local.server.port"));
        return "Deleted user with id " + id + " and JWT subject " + jwt.getSubject();
    }


    @PostAuthorize("returnObject.userId == #jwt.subject")
    @GetMapping(path = "/{id}")
    public UserVO getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        System.out.println("Port number: " + environment.getProperty("local.server.port"));
        return new UserVO("Venkatram", "Veerareddy","4704df79-a69d-4995-90d6-6cf6536aa09f");
    }
}
