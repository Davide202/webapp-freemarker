package spring.mvc.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.mvc.webapp.model.User;

@Controller
@RequestMapping(value = "/input")
public class FormController {

    // http://localhost:8080/input/form
    @GetMapping("/")
    public String index() {
        return "redirect:/form";
    }

    @GetMapping("/form")
    public String formGet(Model model) {
        return "form";
    }

    @PostMapping("/form")
    public String formPost(User user, Model model) {
        model.addAttribute("user", user);
        System.out.println(user.toString());
        return "form";
    }
}
