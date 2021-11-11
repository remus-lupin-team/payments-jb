package com.bootcamp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// i am not sure if i were supposed to build this class. i want to open card.html
// it does not open from here apparently

@Controller
public class CardController {
    @RequestMapping("/card")
    public String card() {
        return "card";
    }
}
