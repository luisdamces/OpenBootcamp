package com.example.springboot_rest_jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


// Un ejemplo con las "annotations" de Spring MVC
@Controller
@SuppressWarnings("unused")
public class HelloController {

    @GetMapping("/hello")
    public @ResponseBody String sayHello() {
        return "Hello ;)";
    }

    // Puedo devolver código HTML que el navegador interpreta,
    // aunque no es una práctica recomendada.
    @GetMapping("/getHTML")
    public @ResponseBody String returnHTML() {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Prueba con Spring MVC</title>
                </head>
                <body>
                    <h1 style="color: red">Hola Spring MVC ;)</h1>
                </body>
                </html>
                """;
        }
}