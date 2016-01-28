package com.spring.jetty.web;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.jetty.others.SpringException;
import com.spring.jetty.service.HelloWorldService;

/**
 * @author 7827983
 *
 */
@Controller
public class WelcomeController {

  private final Logger logger = Logger.getLogger(WelcomeController.class.getName());
  
  private final HelloWorldService helloWorldService;
  
  @Autowired
  public WelcomeController(HelloWorldService pHelloWorldService) {
    this.helloWorldService = pHelloWorldService;
  }
  
  @RequestMapping(value="/" , method=RequestMethod.GET)
  public String index(Map<String, Object> model) {
    logger.info(" index is executed!");
    model.put("title", helloWorldService.getTitle(""));
    model.put("msg", helloWorldService.getDesc());
   return "index";
  }

  @RequestMapping(value="/hello/{name:.+}", method=RequestMethod.GET)
  public ModelAndView hello(@PathVariable("name") String name) {
    logger.info(" hello is executed!");
    
    if (name.length() < 4)
      throw new SpringException("Given name is too short"); 
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("index");
    mav.addObject("title" , helloWorldService.getTitle(name));
    mav.addObject("msg", helloWorldService.getDesc());
    return mav;
  }
  
  @ExceptionHandler(value={SpringException.class})
  public ModelAndView exception (SpringException exception) {
    logger.info(" exception is executed!");
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("ExceptionPage");
    mav.addObject("exception", exception);
    return mav;
  }  
}
