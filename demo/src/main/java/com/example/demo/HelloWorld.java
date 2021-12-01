package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/") // Criação da rota - / = end point principal
public class HelloWorld {

    @GetMapping // Método Get sendo aplicado.
    public @ResponseBody String sayHello() {
        return "Hello Man!";
    }

    @GetMapping("/subpath")
    public @ResponseBody String subPath() {
        return "Outro Hello Man!";
    }

    @GetMapping("/{dynamic}")
    public @ResponseBody String dynamicPath(@PathVariable("dynamic") String name) {
        return "Teste de dinâmico: " + name;
    }

    @GetMapping("/subpath2")
    public @ResponseBody String subPath2(@RequestParam("name") String name) {
        return "Subpath 2 Hello: " + name;
    }

    @GetMapping("/subpath3")
    public @ResponseBody String subPath3(final WebRequest webRequest) {
        String name = webRequest.getParameter("name");
        return "Subpath 3 Hello: " + name;
    }

    @GetMapping("/subpath4")
    public @ResponseBody String subPath4(@RequestParam(value="name", required = true) String name) {
        return "Subpath 4 Hello: " + name;
    }


}
