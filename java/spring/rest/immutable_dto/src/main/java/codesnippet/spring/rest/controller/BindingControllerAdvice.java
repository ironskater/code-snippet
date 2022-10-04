package codesnippet.spring.rest.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BindingControllerAdvice {

   @InitBinder
   public void initBinder(WebDataBinder binder) {
       binder.initDirectFieldAccess();
   }
}
