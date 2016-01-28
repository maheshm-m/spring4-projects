package spring.controller;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("/submission")
public class SubmissionController {

  Logger logger = Logger.getLogger(this.getClass().getName());
  
  @RequestMapping(value="/model/{name}", method = RequestMethod.GET)
  public String getModel(@PathVariable String name, Model m) {
    String result = String.format("Hello %s" ,name);
    logger.info("getModel called " + m);
    return result;
  }
 
  @RequestMapping(value="/files" , method= RequestMethod.GET)
  public void listFiles(Model m) {
    logger.info("listFiles called " + m);
  }
  
  @RequestMapping(value="/files" , method= RequestMethod.POST)
  @ResponseStatus(value=HttpStatus.CREATED)
  public void createFiles(Model m, HttpServletRequest request, HttpServletResponse response) {
    logger.info("createFiles called " + m);
    Employee emp = new Employee();
    emp.setName("Header Testing");
    response.addHeader("Location", getLocationForChildResource(request, emp));
  }
  
  private String getLocationForChildResource(HttpServletRequest request, Employee emp) {
    StringBuffer url = request.getRequestURL();
    UriTemplate uriTemplate = new UriTemplate(url.append("/model/{name}").toString());
    return uriTemplate.expand(emp.getName()).toASCIIString();
  }
  
  @RequestMapping(value="/files/model/{name}", method = RequestMethod.GET)
  public String getModelChild(@PathVariable String name, Model m) {
    String result = String.format("Hello %s" ,name);
    logger.info("getModelChild called " + m);
    return result;
  }
  

  @RequestMapping(value="/files/{fileId}" , method= RequestMethod.GET)
  public void createFilesError(@PathVariable String fileId, HttpServletRequest request, HttpServletResponse response) {
    logger.info("createFilesError called ");
    
    if ("FILE1".equals(fileId)) {
      throw new NotFoundException(String.format("File %s not found", fileId));
    }
  }
  
}

