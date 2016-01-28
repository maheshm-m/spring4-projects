package spring.controller;


public class Greeting {
  private int id;
  private String greeting;
  /**
   * @return the id
   */
  public int getId() {
    return id;
  }
  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }
  /**
   * @return the greeting
   */
  public String getGreeting() {
    return greeting;
  }
  /**
   * @param greeting the greeting to set
   */
  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Greeting [id=" + id + ", greeting=" + greeting + "]";
  }
  
  
  
}

