package com.example.restdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // говорит спрингу что этот класс это контроллер. Нужно создать его объект и хранить в контексте.
// и пытаться дать обработать HTTP запросы этому клссу
public class GreetingController {
    @Value("${some.greeting}") // получить значиение some.greeting из application.properties конфига
    private String greeting;

    @GetMapping("/greeting")
    public String greeting() {
        return greeting;
    }
}
