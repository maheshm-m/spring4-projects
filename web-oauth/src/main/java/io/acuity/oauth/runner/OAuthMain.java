/**
 * 
 */
package io.acuity.oauth.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import io.acuity.oauth.config.AuthorisationServerConfiguration;
import io.acuity.oauth.config.SecurityConfiguration;

/**
 * @author Amit Verma
 *
 */
@SpringBootApplication
public class OAuthMain extends SpringBootServletInitializer {

    /**
     * 
     */
    public OAuthMain() {
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(new Object[] { OAuthMain.class, 
                AuthorisationServerConfiguration.class,
                SecurityConfiguration.class}, args);
        // new SpringApplicationBuilder(Main.class, MessageController.class)
        // .profiles("server")
        // .web(true)
        // .sources(MessageController.class)
        // .application()
        // .run(args);
    }

    @Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.setPort(9092);
        return tomcat;
    }

}
