package com.openclassrooms.watchlist.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CustomErrorController implements ErrorController {

    private final Logger logger= LoggerFactory.getLogger(CustomErrorController.class);
    @Override
    public String getErrorPath(){
        return "/error";
    }
    @GetMapping("/error")
    public ModelAndView handleError(HttpServletResponse response) {
        int code=response.getStatus();
       // Object status = r.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
       System.out.println("Error with status code " + code + " happened. Support! Do something about it!");
        logger.error("Error with code {} happened ! Do Something !!!",code);
        return new ModelAndView("error");
    }
}
