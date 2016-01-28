/**
 * 
 */
package io.acuity.web.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import io.acuity.web.config.ApplicationConfiguration;
import io.acuity.web.config.ResourceServerConfiguration;
import io.acuity.web.controller.mvc.HomeController;
import io.acuity.web.controller.rest.MessageController;

/**
 * @author Amit Verma
 *
 */
@SpringBootApplication
public class Main extends SpringBootServletInitializer {

    /**
     * 
     */
    public Main() {
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(new Object[] 
                {       Main.class, 
                        MessageController.class, 
                        HomeController.class, 
                        ApplicationConfiguration.class,
                        ResourceServerConfiguration.class
                        
                 }, args);
        // new SpringApplicationBuilder(Main.class, MessageController.class)
        // .profiles("server")
        // .web(true)
        // .sources(MessageController.class)
        // .application()
        // .run(args);
    }

}
