package com.lin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Lin on 2018/11/19.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView welcome(HttpServletRequest request) {
        String name = request.getParameter("name");

        ModelAndView modelAndView = new ModelAndView("welcome");
        modelAndView.addObject("name", name);

        return modelAndView;
    }

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
        String name = request.getParameter("name");

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("name", name);

        return modelAndView;
    }


}
