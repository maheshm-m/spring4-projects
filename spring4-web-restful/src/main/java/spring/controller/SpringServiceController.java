package spring.controller;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/service/greeting")
public class SpringServiceController {

  Logger logger = Logger.getLogger(this.getClass().getName());
  
  @RequestMapping(value="/{name}", method = RequestMethod.GET)
  public String getGreeting(@PathVariable String name) {
    String result = String.format("Hello %s" ,name);
    logger.info("getGreeting called " + result);
    return result;
  }
  
  
}

