package com.lin.controller;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Lin on 2018/11/19.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request) {
        String name = request.getParameter("name");

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("name", name);

        return modelAndView;
    }

}
