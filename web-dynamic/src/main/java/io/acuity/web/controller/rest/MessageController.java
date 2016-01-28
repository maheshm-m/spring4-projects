package io.acuity.web.controller.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.acuity.web.model.Message;

@RestController
@RequestMapping("/sayhello")

public class MessageController {

    /**
     * Method for handling file download request from client
     */
    @RequestMapping(method = RequestMethod.GET,produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Message sayHello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        return new Message("Hello World in bootstrap & knockout.!!", null);
    }

    @RequestMapping(method = RequestMethod.POST,produces = APPLICATION_JSON_VALUE)
    public Message sayHelloToName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        return new Message("Hello World in bootstrap & knockout.!!", null);
    }

}
