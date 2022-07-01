package com.mediscreen.UserInterfaceApp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiabetesAppController {


    private static final Logger logger = LogManager.getLogger("DiabetesAppController");

    /**
     *
     * Show homepage
     *
     * @return show the homepage in the view
     */
    @GetMapping(name = "/")
    public String showHomepage() {
        logger.info("New request: show homepage in the view");
        return "home/homepage";
    }
}
