package security.controller;

import java.util.logging.Logger;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import security.model.Employee;

@Controller
public class EditEmployeeController {

  private static final Logger LOG = Logger.getLogger(EditEmployeeController.class.getName());
  
  
  @RequestMapping(value="/", method=RequestMethod.GET)
  public String defaultPage(ModelMap modelMap) {
    LOG.info("Inside defaultPage method");
    return "redirect:/list";
  }
  
  @RequestMapping(method=RequestMethod.GET, value="/list")
  public String listEmployee(ModelMap modelMap) {
    LOG.info("Inside listEmployee method");
    Employee emp = new Employee();
    emp.setId(1);
    emp.setName("Mahesh Malakannavar");
    modelMap.addAttribute("employee", emp);
    return "login/editEmployeeList";
  }
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(ModelMap model) {
      LOG.info("Inside login method");
      return "login/login";
  }  
  
  @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
  public String loginerror(ModelMap model) {
      LOG.info("Inside loginerror method");
      model.addAttribute("error", "true");
      return "login/denied";
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout(ModelMap model) {
      LOG.info("Inside logout method");
      return "login/logout";
  }
  
  
  @RequestMapping(method=RequestMethod.POST, params="ok")
  public String ok(Model model, @ModelAttribute("employee") Employee employeeModel, Authentication user) {
    LOG.info("Inside ok method :" +employeeModel.toString());
    LOG.info("Inside ok Authentication :" + user.getName());
    
    return "redirect:/list";
  }
  
  @RequestMapping(method=RequestMethod.POST, params="cancel")
  public String cancel(Model model, @ModelAttribute("employee") Employee employeeModel) {
    LOG.info("Inside cancel method");
    return "redirect:/login";
  }  
}
