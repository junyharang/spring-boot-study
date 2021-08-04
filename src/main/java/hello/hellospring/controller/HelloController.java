package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    } // hello() 끝

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    } // helloMvc() 끝

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    } // helloString() 끝

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
       Hello hello = new Hello();
       hello.setName(name);

       return hello;
    } // helloAPI() 끝

    static class Hello {
        private String name;


        // Getter와 Setter를 이용한 자바 Bean 방식은 property 접근 방식
        public String getName() {
            return name;
        } // getName()끝

        public void setName(String name) {
            this.name = name;
        } // setName() 끝

    } // Hello() 끝


} // Class 끝
