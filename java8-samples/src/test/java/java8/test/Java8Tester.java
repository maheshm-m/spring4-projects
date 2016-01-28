package java8.test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author 7827983
 *
 */
public class Java8Tester {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    sortUsingJava8();
    //mathOperation();
    //predicate();
    
    //Car car = new Java8Tester().new Car();
    //car.print();
    
    //statistics_Collectors();
    
    //optional();
    
  }

  private static void optional(){
    List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
    
    Optional<String>of = Optional.ofNullable("Mahesh");
//    Optional<String>of = Optional.ofNullable(null);
    String orElse = of.orElse("Manu");
    System.out.println("Value " + orElse);
//    of.orElseThrow(IllegalArgumentException::new);
    
    
    LocalDateTime ldt = LocalDateTime.now();
    System.out.println("Local Date and Time " + ldt);
    
    System.out.println(" toLocalDate is - " + ldt.toLocalDate());
    System.out.println(" toLocalTime is - " + ldt.toLocalTime());
    System.out.println(" toLocalTime is - " + ldt);
    ZonedDateTime atZone = ldt.atZone(ZoneId.of("GMT"));
    
 // Get the current date and time
    ZonedDateTime date1 = ZonedDateTime.parse("2007-12-03T10:15:30+05:30[Asia/Dhaka]");
    System.out.println("date1: " + date1);
      
    ZoneId id = ZoneId.of("Europe/Paris");
    System.out.println("ZoneId: " + id);
      
    ZoneId currentZone = ZoneId.systemDefault();
    System.out.println("CurrentZone: " + currentZone);    
    
    Date date = new Date();
    Instant instant = date.toInstant();
    Instant now = Instant.now();
    
    LocalDateTime ofInstant = LocalDateTime.ofInstant(now, ZoneId.systemDefault());
    System.out.println("ofInstant: " + ofInstant);
    LocalDateTime ofInstant1 = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    System.out.println("ofInstant 1 : " + ofInstant1);
   
    
    
  }
  
  private static void  statistics_Collectors(){
    List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

    IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

    System.out.println("Highest number in List : " + stats.getMax());
    System.out.println("Lowest number in List : " + stats.getMin());
    System.out.println("Sum of all numbers : " + stats.getSum());
    System.out.println("Average of all numbers : " + stats.getAverage());
    
    List<Integer> numbers1 = Arrays.asList(3, 1, 0, 10);    
    
    IntSummaryStatistics stats1 = numbers1.stream().mapToInt((x) -> x ).summaryStatistics();    
    
    stats1.combine(stats);
    
    System.out.println("Highest number in List : " + stats1.getMax());
    System.out.println("Lowest number in List : " + stats1.getMin());
    System.out.println("Sum of all numbers : " + stats1.getSum());
    System.out.println("Average of all numbers : " + stats1.getAverage());
    
  }
  
  private static void predicate() {
    Predicate<Integer> predicate1 = n -> n == 0; 
    Predicate<Integer> predicate2 = n -> n > 0; 
    Predicate<Integer> predicate3 = n -> n < 0;
    
    System.out.println("Predicate Test 1 = " + predicate1.test(0));
    System.out.println("Predicate Test 2 = " + predicate2.test(1));
    System.out.println("Predicate Test 3 = " + predicate3.test(-1));
  }
  
  private static void sortUsingJava8() {
    List<String> strList = new ArrayList<>();
    strList.add("B");
    strList.add("C");
    strList.add("D");
    strList.add("E");
    strList.add("A");
    strList.add("X");
    strList.add("D");    

    // Collections.sort(strList, new Comparator<String>() {
    // /* (non-Javadoc)
    // * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
    // */
    // @Override
    // public int compare(String o1, String o2) {
    // // TODO Auto-generated method stub
    // return o1.compareTo(o2);
    // }
    // });

    Collections.sort(strList, (s1, s2) -> s1.compareTo(s2));

    strList.forEach(System.out::println);
  }

  
  
  private static void mathOperation() {
    // With type declaration
    MathOperation moAdd = (int a, int b) -> (a + b);

    MathOperation moMultiply = (a, b) -> (a * b);
    
    // With curly braces
    MathOperation moSubstract = (a, b) -> { return (a - b); };
    
    int a = 10;
    int b = 5;
    
    System.out.println(String.format(" Number %s and %s = %s", a, b, moAdd.operation(a,  b)));
    System.out.println(String.format(" Number %s ", operate(a, b, moAdd)));    
    System.out.println(String.format(" Number %s and %s = %s", a, b, moMultiply.operation(a,  b)));
    System.out.println(String.format(" Number %s and %s = %s", a, b, moSubstract.operation(a,  b)));

    GreetingServie greetingServie = (message) -> { return ("The message is " + message); };
    GreetingServie greetingServie1 = message -> { return ("The message is " + message); };

    System.out.println(greetingServie.sayMessage("Mahesh"));
    System.out.println(greetingServie1.sayMessage("Manu"));
    
  }

  interface MathOperation {
    int operation(int a, int b);
  }

  interface GreetingServie {
    String sayMessage(String message);
  }

  private static int operate(int a, int b, MathOperation mathOperation) {
    return mathOperation.operation(a, b);
  }
  
  interface Vehicle {
    default void print() {
      String str = "I am Vehicle";
      System.out.println(str);
    }
    
    static void blowHorn(){
      System.out.println("Blowing Horn ....");
    }
  }

  interface FourWheeler {
    default void print() {
      System.out.println("I am Four Wheeler");
    }
  }
  
  class Car implements Vehicle , FourWheeler {

    /* (non-Javadoc)
     * @see java8.test.Java8Tester.Vehicle#print()
     */
    @Override
    public void print() {
      Vehicle.super.print();
      FourWheeler.super.print();
      Vehicle.blowHorn();
      System.out.println("I am in Car");
    }
    
  }
}
