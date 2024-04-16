package spring.mvc.webapp.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@Log4j2
public class MvcTestController {

    // https://www.javainuse.com/spring/spring-boot-freemarker-hello-world

    // http://localhost:8080/test

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String init( Model model) {

        model.addAttribute("name", "Davideeeeeee");
        return "test-template2";
    }
    // http://localhost:8080/indice
    @GetMapping(value = {"/indice"})
    public String index(Model model) {
        model.addAttribute("title", "Titolo di test");
        return "index";
    }

}
