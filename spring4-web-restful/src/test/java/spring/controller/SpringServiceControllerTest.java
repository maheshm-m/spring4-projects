package spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;


public class SpringServiceControllerTest {

  public static final String SERVER_URI = "http://localhost:8080/RESTFul";
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    //getGreeting();
    getGreetingTwo();
  }

  private static void getGreeting() {
    RestTemplate template = RestTemplateHelper.getRestTemplate();
    String forObject = template.getForObject(SERVER_URI+"/service/greeting/Mahesh", String.class);
    System.out.println(" Test == " + forObject);
  }
  private static void getGreetingTwo() {
    RestTemplate template = RestTemplateHelper.getRestTemplate();
    Map<String, String> params = new HashMap<String, String>();
    params.put("name", "ManuTanu");
    String forObject = template.getForObject(SERVER_URI+"/service/greeting/{name}", String.class, params);
    System.out.println(" Test Two == " + forObject);
  }
  
}

