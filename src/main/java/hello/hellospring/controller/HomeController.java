package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // localhost:8080을 입려갛고 들어오면 처음 만나는 곳
    @GetMapping("/")
    public String home() {
        // home.html로 보낸다.
        return "home";
    }
}
