package com.andreigravonski.adotepet.controller;

import com.andreigravonski.adotepet.exception.EmailJaCadastradoExcepition;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EmailJaCadastradoExcepition.class)
    public String handleEmailJaCadastrado(EmailJaCadastradoExcepition ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensagemErro", ex.getMessage());
        return "redirect:/registro";
    }

}
