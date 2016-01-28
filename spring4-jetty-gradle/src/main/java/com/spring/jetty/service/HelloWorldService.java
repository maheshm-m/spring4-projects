package com.spring.jetty.service;

import java.util.Objects;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

/**
 * @author 7827983
 *
 */
@Service
public class HelloWorldService {
  private final Logger logger = Logger.getLogger(HelloWorldService.class.getName());
  
  public String getDesc() {
    logger.info("getDesc() is executed!");
    return "Gradle + Spring MVC Hello World Example";
  }

  public String getTitle(String name) {
      logger.info(String.format("getTitle() is executed! %s", name));
      if(name == null){
          return "Hello World";
      }else{
          return "Hello " + name;
      }
  }
}

