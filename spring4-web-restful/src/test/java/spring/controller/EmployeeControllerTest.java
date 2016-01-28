package spring.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import spring.controller.EmpRestURIConstants;
import spring.controller.Employee;

@SuppressWarnings({ "unused", "unchecked","rawtypes"})
public class EmployeeControllerTest {

  public static final String SERVER_URI = "http://localhost:8080/RESTFul";
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
//    System.out.println("***** testGetDummyEmployee() ***** ");    
//    testGetDummyEmployee();
//    System.out.println("***** testCreateEmployee ***");
//    testCreateEmployee();

    //System.out.println("***** testCreateListEmployee ***");
    //testCreateListEmployee();
    
    //System.out.println("***** testUpdateEmployee ***");
    //testUpdateEmployee();
    
    //System.out.println("***** testUpdateEmployeeObject ***");    
    //testUpdateEmployeeObject();
    
//    System.out.println("***** testupdateEmployeeId ***");
//    testupdateEmployeeId();
    
    //System.out.println("***** testDeleteEmployee ***");
    //testDeleteEmployee();
    
    System.out.println("***** testGetEmployeeCallGetGreeting ***");
    testGetEmployeeCallGetGreeting();
  }

  private static void testGetDummyEmployee() {
    RestTemplate restTemplate = new RestTemplate();
    MappingJackson2HttpMessageConverter mc = new MappingJackson2HttpMessageConverter();
    List listMC = new ArrayList<MappingJackson2HttpMessageConverter>();
    listMC.add(mc);
    restTemplate.setMessageConverters(listMC);
    Employee emp = restTemplate.getForObject(SERVER_URI+"/rest/emp/dummy", Employee.class);
    printEmpData(emp);
  }

  private static void testCreateEmployee() {
    RestTemplate restTemplate = RestTemplateHelper.getRestTemplateWithJackson();
    Employee emp = new Employee();
    emp.setId(1);
    emp.setName("Mahesh");
    Employee response = restTemplate.postForObject(SERVER_URI+EmpRestURIConstants.CREATE_EMP, emp, Employee.class);
    printEmpData(response);
}  
  
  private static void testCreateListEmployee() {
    RestTemplate restTemplate = RestTemplateHelper.getRestTemplateWithJackson();
    
    List<Employee> listEmployee = new ArrayList<Employee>();
    
    Employee emp = new Employee();
    emp.setId(1);
    emp.setName("Mahesh 01");
    emp.setCreatedDate("02/11/2015");
    listEmployee.add(emp);
    emp = new Employee();
    emp.setId(2);
    emp.setName("Mahesh 02");
    emp.setCreatedDate("01/10/2014");
    listEmployee.add(emp);
    Employee[] response = restTemplate.postForObject(SERVER_URI+"/rest/emp/createList", listEmployee, Employee[].class);
    System.out.println(" Response = " + response);
    
    List<Employee> asList = Arrays.asList(response);
    
      Iterator<Employee> iterator = asList.iterator();
      while (iterator.hasNext()) {
        printEmpData(iterator.next());  
      }
      
     }  
  
  private static void testUpdateEmployee() {
    RestTemplate restTemplate = RestTemplateHelper.getRestTemplateWithJackson();
    Employee emp = new Employee();
    emp.setId(100);
    emp.setName("Mahesh 100");
    
    //String postForEntity = restTemplate.postForObject(SERVER_URI+"/rest/emp/update", emp, String.class);
    ResponseEntity<String> postForEntity = restTemplate.postForEntity(SERVER_URI+"/rest/emp/update", emp, String.class);
    System.out.println( " Response is = " + postForEntity);
    System.out.println( " Response Status Code is = " + postForEntity.getStatusCode());
  } 
  
  private static void testUpdateEmployeeObject() {
    RestTemplate restTemplate = RestTemplateHelper.getRestTemplateWithJackson();
    Employee emp = new Employee();
    emp.setId(100);
    emp.setName("Mahesh updateEmployeeObject 100");
    
    ResponseEntity<Employee> postForEntity = restTemplate.postForEntity(SERVER_URI+"/rest/emp/updateObject", emp, Employee.class);
    System.out.println( " Response is = " + postForEntity);
    System.out.println( " Response getBody is  = " + postForEntity.getBody().getName());
    System.out.println( " Response Status Code is = " + postForEntity.getStatusCode());
  }
  
  private static void testupdateEmployeeId() {
    RestTemplate restTemplate = RestTemplateHelper.getRestTemplateWithJackson();
    Employee emp = new Employee();
    emp.setId(100);
    emp.setName("Mahesh updateEmployeeObject 100");
    
    Map<String, String> params = new HashMap<String, String>();
    params.put("id", "2");
    
    //restTemplate.put(SERVER_URI+"/rest/emp/updateEmployee/{id}", emp, params);
    
    ResponseEntity<Employee> postForEntity = restTemplate.postForEntity(SERVER_URI+"/rest/emp/updateEmployee/{id}", emp, Employee.class, params);
    
    System.out.println( " Response is = " + postForEntity);
    System.out.println( " Response getBody is  = " + postForEntity.getBody().getName());
    System.out.println( " Response Status Code is = " + postForEntity.getStatusCode());    
  }  
  
  private static void testDeleteEmployee() {
    RestTemplate restTemplate = RestTemplateHelper.getRestTemplateWithJackson();
    
    Map<String, String> params = new HashMap<String, String>();
    params.put("id", "2");
    restTemplate.delete(SERVER_URI+"/rest/emp/deleteById/{id}", params);
  }
  
  private static void testGetEmployeeCallGetGreeting() {
    RestTemplate restTemplate = RestTemplateHelper.getRestTemplateWithJackson();
    
    Map<String, String> params = new HashMap<String, String>();
    params.put("greetings", "Great  Morning!");
    ResponseEntity<Greeting> postForEntity = restTemplate.getForEntity(SERVER_URI+"/rest/emp/greeting/{greetings}", Greeting.class , params);    
    System.out.println( " Response is = " + postForEntity);
    System.out.println( " Response getBody is  = " + postForEntity.getBody());
    System.out.println( " Response Status Code is = " + postForEntity.getStatusCode());    
  }
  
  public static void printEmpData(Employee emp) {
    System.out.println("ID="+emp.getId()+",Name="+emp.getName()+",CreatedDate="+emp.getCreatedDate());
  }
}

