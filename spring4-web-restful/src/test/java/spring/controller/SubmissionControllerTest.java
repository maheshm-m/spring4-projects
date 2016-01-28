package spring.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


public class SubmissionControllerTest {

  public static final String SERVER_URI = "http://localhost:8080/RESTFul/submission";
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    //getGreeting();
    createFilesError();
  }

  private static void createFilesError() {
    RestTemplate template = RestTemplateHelper.getRestTemplate();
    try {
      String forObject = template.getForObject(SERVER_URI+"/files/FILE1", String.class);
      System.out.println(" Response is " + forObject);
    } catch (HttpClientErrorException ne) {
      System.out.println(" File not found ! " + ne.getMessage());
    }
  }
}

