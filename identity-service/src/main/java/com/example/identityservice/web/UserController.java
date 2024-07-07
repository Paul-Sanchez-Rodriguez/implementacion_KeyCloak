package com.example.identityservice.web;

import com.example.identityservice.dto.ResponseMessage;
import com.example.identityservice.dto.User;
import com.example.identityservice.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private KeycloakService keycloakService;

    @PostMapping("/create")
    public Mono<ResponseEntity<ResponseMessage>> create(@RequestBody User user) {
        return keycloakService.createUser(user)
                .map(obj -> {
                    int status = (int) obj[0];
                    ResponseMessage message = (ResponseMessage) obj[1];
                    return ResponseEntity.status(status).body(message);
                })
                .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body(new ResponseMessage("Error en la solicitud"))));
    }

    @GetMapping("/hola")
    public String helloAdmin(){
        return "Hello Sprig Boot With Keycloak with ADMIN";
    }
}
