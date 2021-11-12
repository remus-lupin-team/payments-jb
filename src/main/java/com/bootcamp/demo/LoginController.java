package com.bootcamp.demo;

import com.bootcamp.demo.model.Card;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    //probably the next lines should not be here, but I am trying to see the card.html via localhost
//    @RequestMapping("/card")
//    public String card() {
//        return "card";
//    }
//
//    //checking if Card created successfully
//    @PostMapping("/card")
//    public String submitCard(@ModelAttribute("card") Card card) {
//        try{
//            System.out.println(card.toString());
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        return "card";
//    }
//
//    //trying to display an error message if user inputs inappropriate data, but it does not work :(
//    @ExceptionHandler(value = {Card.class})
//    public String saveCardError(Model model) {
//        System.out.println("Display input card error");
//        model.addAttribute("cardError", true);
//        return "card";
//    }
}
