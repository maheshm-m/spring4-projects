package spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.client.RestTemplate;

//@EnableWebMvc
@RestController
public class EmployeeController {

  private static final Logger logger = Logger.getLogger(EmployeeController.class.getClass().getName());
  
  //Map to store employees, ideally we should use database
  Map<Integer, Employee> empData = new HashMap<Integer, Employee>();  
  
//  @RequestMapping(value = EmpRestURIConstants.DUMMY_EMP, method = RequestMethod.GET 
//      , produces = "application/json",headers="Accept=*/*")
// , produces = MediaType.APPLICATION_JSON_VALUE  
  @RequestMapping(value = "/rest/emp/dummy", method = RequestMethod.GET)
  public Employee getDummyEmployee() {
      logger.info("Start getDummyEmployee");
      Employee emp = new Employee();
      emp.setId(9999);
      emp.setName("Dummy");
      empData.put(emp.getId(), emp);
      return emp;
  }
  
  @RequestMapping(value = "/rest/emp/{id}", method = RequestMethod.GET)
  public Employee getEmployee(@PathVariable("id") int empId) {
      logger.info("Start getEmployee. ID="+empId);
      return empData.get(empId);
  }  
  
  @RequestMapping(value = "/rest/emps", method = RequestMethod.GET)
  public List<Employee> getAllEmployees() {
      logger.info("Start getAllEmployees.");
      List<Employee> emps = new ArrayList<Employee>();
      Set<Integer> empIdKeys = empData.keySet();
      for(Integer i : empIdKeys){
          emps.add(empData.get(i));
      }
      return emps;
  }
  
  @RequestMapping(value = "/rest/emp/create", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
  public Employee createEmployee(@RequestBody Employee emp) {
    logger.info("Start createEmployee. Data " + emp.getId() + "==" + emp.getName());
      logger.info("Start createEmployee. Data " + emp != null ? emp.toString() : "Emplyee object is null");
      emp.setCreatedDate("30/10/2015");
      empData.put(emp.getId(), emp);
      return emp;
  }  

  @RequestMapping(value = "/rest/emp/createList", method = RequestMethod.POST)
  public List <Employee> createEmployee(@RequestBody List<Employee> emp) {
      logger.info("Start createEmployee. Data " + emp.size());
      logger.info("Start createEmployee. Data " + emp != null ? emp.get(0).toString() : "Emplyee object is null");
      
      Iterator<Employee> iterator = emp.iterator();
      while (iterator.hasNext()) {
        Employee next = iterator.next() ;
        System.out.println(String.format("Data Id = %s , Name = %s, Created Date = %s" , next.getId(), next.getName(), next.getCreatedDate()));        
      }
      
      return emp;
  }  
  
  
  @RequestMapping(value = "/rest/emp/delete/{id}", method = RequestMethod.PUT)
  public Employee deleteEmployee(@PathVariable("id") int empId) {
      logger.info("Start deleteEmployee. Id " + empId);
      Employee emp = empData.get(empId);
      empData.remove(empId);
      return emp;
  }
  
  @RequestMapping(value = "/rest/emp/deleteById/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") int empId) {
      logger.info("Start deleteEmployee. Id " + empId);
      return new ResponseEntity<String>(HttpStatus.OK);
  }
  
  @RequestMapping(value = "/rest/emp/update", method = RequestMethod.POST)
  public ResponseEntity<String> updateEmployee(@RequestBody Employee emp) {
      logger.info("Start updateEmployee. Id " + emp.toString());
      return new ResponseEntity<String>(HttpStatus.ACCEPTED);
  }  
  
  @RequestMapping(value = "/rest/emp/updateObject", method = RequestMethod.POST)
  public ResponseEntity<Employee> updateEmployeeObject(@RequestBody Employee emp) {
      logger.info("Start updateEmployeeObject. Id " + emp.toString());
      return new ResponseEntity<Employee>(emp, HttpStatus.OK);
  }  
  
  @RequestMapping(value = "/rest/emp/updateEmployee/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Employee> updateEmployeeObject(@PathVariable String id, @RequestBody Employee emp) {
      logger.info("Start PUT Id " + id);
      logger.info("Start updateEmployeeObject. " + emp.toString());
      return new ResponseEntity<Employee>(emp, HttpStatus.OK);
  }  
  
  @RequestMapping(value = "/rest/emp/updateEmployee/{id}", method = RequestMethod.POST)
  public ResponseEntity<Employee> updateEmployeeId(@PathVariable String id, @RequestBody Employee emp) {
      logger.info("Start PUT Id " + id);
      logger.info("Start updateEmployeeObject. " + emp.toString());
      return new ResponseEntity<Employee>(emp, HttpStatus.OK);
  }
  
  @RequestMapping(value = "/rest/emp/greeting/{greetings}", method = RequestMethod.GET)
  public ResponseEntity<Greeting> getEmployeeCallGetGreeting(@PathVariable String greetings) {
      Greeting greeting = new Greeting();
      greeting.setGreeting("Sending " + greetings);
      logger.info("Start getEmployeeCallGetGreeting == " + greetings);
      
      RestTemplate restTemplate = RestTemplateHelper.getRestTemplate();
      Map<String, String> params = new HashMap<String, String>();
      params.put("name", greeting.getGreeting());
      String postForEntity = 
          restTemplate.getForObject(RestTemplateHelper.SERVER_URI+"/service/greeting/{name}", String.class , params);
      greeting.setGreeting(postForEntity);
      return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
  }  
}

