package spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


public class RestTemplateHelper {
  public static final String SERVER_URI = "http://localhost:8080/RESTFul";
  /**
   * @return
   */
  public static RestTemplate getRestTemplateWithJackson() {
    RestTemplate restTemplate = new RestTemplate();
    HttpMessageConverter mc = new MappingJackson2HttpMessageConverter();
    List listMC = new ArrayList<HttpMessageConverter>();
    listMC.add(mc);
    restTemplate.setMessageConverters(listMC);
    return restTemplate;
  }  

  /**
   * @return
   */
  public static RestTemplate getRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate;
  }  
}

