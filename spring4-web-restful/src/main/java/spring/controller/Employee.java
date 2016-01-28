package spring.controller;


public class Employee {
  private int id;
  private String name;
  private String createdDate;
   
  public int getId() {
      return id;
  }
  public void setId(int id) {
      this.id = id;
  }
  public String getName() {
      return name;
  }
  public void setName(String name) {
      this.name = name;
  }
  /**
   * @return the createDate
   */
  public String getCreatedDate() {
    return createdDate;
  }
  /**
   * @param createdDate the createdDate to set
   */
  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }
  @Override
  public String toString() {
    return "Employee [id=" + id + ", name=" + name + ", createdDate=" + createdDate + "]";
  }
}

