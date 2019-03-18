package com.example.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pooya on 3/18/2019.
 */

@Controller
public class IndexController {

    @RequestMapping({"", "/", "index"})
    public String getIndex() {
        return "index";
    }

}
