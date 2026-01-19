package com.aestron.gestao.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception ex) {
        log.error("╔═══════════════════════════════════════════════════════════════");
        log.error("║ ERRO CAPTURADO NO GLOBAL EXCEPTION HANDLER");
        log.error("╠═══════════════════════════════════════════════════════════════");
        log.error("║ URL Requisitada: {}", request.getRequestURL());
        log.error("║ Método HTTP: {}", request.getMethod());
        log.error("║ Tipo de Exceção: {}", ex.getClass().getName());
        log.error("║ Mensagem: {}", ex.getMessage());
        log.error("╠═══════════════════════════════════════════════════════════════");
        log.error("║ STACK TRACE COMPLETO:");
        log.error("╚═══════════════════════════════════════════════════════════════", ex);
        
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorMessage", ex.getMessage());
        mav.addObject("errorType", ex.getClass().getSimpleName());
        mav.addObject("url", request.getRequestURL());
        return mav;
    }
}
