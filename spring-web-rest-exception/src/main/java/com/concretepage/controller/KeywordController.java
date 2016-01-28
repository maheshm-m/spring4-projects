package com.concretepage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.concretepage.bean.ErrorDetail;
import com.concretepage.exception.KeywordNotFoundException;

@RestController
@RequestMapping("/keyword")
public class KeywordController {

  @ExceptionHandler(KeywordNotFoundException.class)
  public ErrorDetail myError(HttpServletRequest request, Exception exception) {
    ErrorDetail error = new ErrorDetail();
    error.setStatus(HttpStatus.BAD_REQUEST.value());
    error.setMessage(exception.getLocalizedMessage());
    error.setUrl(request.getRequestURL().append("/error/111").toString());
    return error;
  }
  
  @RequestMapping("/info")
  public String info(@RequestParam(value="key") String key, Model model ) {
    if (!"key101".equals(key)) {
      throw new KeywordNotFoundException(key);
      }
    return "success";
  }
}

