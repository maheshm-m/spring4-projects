package java8.test;

/**
 * @author 7827983
 *
 */
public final class ImmutableObject {
  
  private final String name;
  
  /**
   * 
   */
  private ImmutableObject(String name) {
    this.name = name;
  }
  
  public static ImmutableObject CreateImmutableObject (String name) {
    return new ImmutableObject(name);
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
}

