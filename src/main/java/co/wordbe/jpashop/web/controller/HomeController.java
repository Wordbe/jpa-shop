package co.wordbe.jpashop.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "사과");
        return "hello";
    }

    @GetMapping("/")
    public String home() {
        log.info("home controller");
        return "home";
    }
}
