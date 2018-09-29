package com.dincrash.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class mainController {

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String Index(){

        return "index";

    }
    @RequestMapping(value = { "/ArchiveClients" }, method = RequestMethod.GET)
    public String ArchiveClients(){

        return "ArchiveClients";

    }
    @RequestMapping(value = { "/ActiveClients" }, method = RequestMethod.GET)
    public String ActiveClients(){

        return "ActiveClients";

    }
}
