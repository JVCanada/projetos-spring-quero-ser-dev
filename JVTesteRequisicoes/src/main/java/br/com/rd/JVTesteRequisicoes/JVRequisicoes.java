package br.com.rd.JVTesteRequisicoes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/") // Criação da rota - / = end point principal
public class JVRequisicoes {

    // EXERCICIO 1
    @GetMapping
    public @ResponseBody String sayHello() {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

        return timeStamp;
    }

    // EXERCICIO 2
    @GetMapping("/CaminhoNome")
    public @ResponseBody String subPath2(@RequestParam("name") String name) {
        return "Bem vindo: " + name;
    }

    // EXERCICIO 3
    @GetMapping("/{dynamic}")
    public @ResponseBody String dynamicPath(@PathVariable("dynamic") String nomeEnd) {
        return "Voce está no endpoint dinâmico: /" + nomeEnd;
    }

    // EXERCICIO 4

    @GetMapping("/CalcularPotencia")
    public @ResponseBody String subPath3(final WebRequest webRequest) {
        Integer numero = Integer.parseInt(webRequest.getParameter("numero"));
        Double potencia = Math.pow(numero, numero);
        return "Potencia: " + potencia;
    }

    // EXERCICIO 5

    @GetMapping("/CalcularSoma")
    public @ResponseBody String subPath4(@RequestParam(value="numero1", required = true) Integer numero1, @RequestParam(value="numero2", required = true) Integer numero2) {
        Integer soma = numero1 + numero2;
        return "Soma dos valores: " + soma;
    }

    // postmap (recebe-se um objeto)

    @PostMapping("/PrimeiroPost")
    public @ResponseBody String primeiroPost(@RequestBody Map<String, Object> teste) {
        return teste.get("attribute").toString() + " - " + teste.get("texto").toString();
    }

    @PostMapping("/SegundoPost")
    public @ResponseBody String segundoPost(@RequestBody Carro car) {
        return car.getModelo() + " - " + car.getAno();
    }

    @PostMapping("/TerceiroPost")
    public @ResponseBody String terceiroPost(@RequestBody String teste) {
        return teste;
    }



}
