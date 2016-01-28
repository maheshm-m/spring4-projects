package io.acuity.web.model;

/**
 * @author Amit Verma
 *
 */
public class Message {

    private final String message;

    private final String name;

    /**
     * @param message
     */
    public Message(String message, String name) {
        super();
        this.message = message;
        this.name = name;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    
    
    
}

