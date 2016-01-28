package io.acuity.web.controller.mvc;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Amit Verma
 *
 */
@Controller
public class HomeController {


    @RequestMapping(method = RequestMethod.GET, value="/home")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String index(Model model) {
        return "index";
    }
}

