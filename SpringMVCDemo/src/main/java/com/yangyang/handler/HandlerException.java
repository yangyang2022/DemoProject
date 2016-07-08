package com.yangyang.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView handException(Exception e){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("ex",e.getMessage());
        return mv;
    }
}
