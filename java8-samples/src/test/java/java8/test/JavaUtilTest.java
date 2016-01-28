package java8.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.*;
/**
 * @author 7827983
 *
 */
public class JavaUtilTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
    //dummy();
    //find_duplicate_elements_in_an_array();
    //printArray();
    dateAndtime();
  }

  private static void dateAndtime() {
    
    LocalDateTime localDateTime = LocalDateTime.now();
    System.out.println("Local Date " + localDateTime);
    LocalDate localDate = LocalDate.from(localDateTime);
    System.out.println("Local Date " + localDate);
    
    LocalTime localTime = LocalTime.of(13, 10, 59);
    System.out.println("Local Time " + localTime);
    
    LocalDateTime lDateAndTime = LocalDateTime.of(localDate, localTime);
    System.out.println("Local Date and Time " + lDateAndTime);
    
  }
  
  private static void printArray() {
    String[] st1 = {"a","b","c"};
    String[] st2 = {"C","B","A"};
    String[][] arrayOfArray = new String[][] {st1, st2};
    
    //System.out.println(" st1 " + Arrays.toString(st1));
    //System.out.println(" st1 " + Arrays.deepToString(arrayOfArray));
    
    List<String> list = new ArrayList<String>();
    list.add("First");
    list.add("Second");
    list.add("Third");
    list.add("Fourth");
    // Print the list in console
    //System.out.println(list);    
   
    //new Thread(() -> System.out.println("Thread start ...")).start();
    List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
    languages.stream().filter(s -> s.length() > 2);
    
    filter(languages, (str1)-> str1.length() > 4);
    
    filter(languages, (str1)-> str1.length() > 4);
    
    //languages.stream().filter( n -> (n.length() > 4) ).forEach(System.out::println);
    
    Predicate<String> ps1 = (n) -> n.startsWith("J");
    Predicate<String> ps2 = (n) -> n.endsWith("a");    
    
    //languages.stream().filter(ps1.and(ps2)).forEach((n) -> System.out.println("String is :" + n));
    
    List<String> G7 = Arrays.asList("USA","Japan","France","Germany","Italy","U.K","Canada");
    
    String collect = G7.stream().map(m -> m.toUpperCase()).collect(Collectors.joining("|"));
    //System.out.println("Output is - " + collect);
    
    List<Integer> i1 = Arrays.asList(9,10,3,4,7,3,4);
    List<Integer> i2 = i1.stream().distinct().collect(Collectors.toList());
    //i2.forEach(System.out::println);
    
    Stream<Date> stream = Stream.generate(() -> { return new Date();}).limit(2);
    stream.forEach(p -> System.out.println(p));    
    
    Stream.iterate(1, i -> i + 1).limit(5).forEach(System.out::println);;
    
    Stream.generate(new Random()::nextInt).limit(5);
    
  }
  
  public static void filter(List<String> names, Predicate<String> condition) { 
    for(String name: names) { 
      if(condition.test(name)) { 
        System.out.println(name + " "); 
      } 
    } 
  } 
  
  private static void find_duplicate_elements_in_an_array() {
    Integer[] array = {1,2,4,5,6,6,8,2};
    List<Integer> asList = Arrays.asList(array);
    
    int[] arrayInt = {1,2,4,5,6,6,8,2};
    List<int[]> asList1 = Arrays.asList(arrayInt);    
    //System.out.println(asList.stream().allMatch(new HashSet<>()::add));
    
    Set <Integer> set = new HashSet<>(); 
    Set <Integer> setDuplicate = Arrays.stream(array).filter( n -> !set.add(n)).collect(Collectors.toSet());
    System.out.println("setDuplicate : " +setDuplicate);
   
    String[] str = {"C","A","B","E"};
    Stream<String> stream = Stream.of(str); 
    List<String> strings = stream.map(Object::toString)
        .collect(ArrayList<String>::new, ArrayList<String>::add, ArrayList<String>::addAll);
    strings.forEach(System.out::println);
    
  }
  
  
  
  /**
   * 
   */
  public static void dummy() {
    // TODO Auto-generated method stub
    System.out.println((int) (Math.random() * 15));
    
    final String name = "Test Name";
    System.out.println("Name :" + name);
    
    ImmutableObject obj = ImmutableObject.CreateImmutableObject("Object Name");
    System.out.println("Name :" + obj.getName());
    
    JavaUtilTest a = new JavaUtilTest();
    JavaUtilTest b = new JavaUtilTest();
    
    Runnable r1 = () -> { 
      synchronized (a) {
        System.out.println("A");
          try {
            // Adding delay so that both threads can start trying to
            // lock resources
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }        
        synchronized (b) {
          System.out.println("B");
        }
      }
    };
    
    
    Runnable r2 = () -> {
      synchronized (b) {
        System.out.println("B");
        synchronized (a) {
          System.out.println("A");
        }
      }
    };
    
    new Thread(r1).start();
    new Thread(r2).start();
    
    Map<String, String> map = new HashMap<>();
    
    Map<String, String> hm = Collections.synchronizedMap(map);
    
  }
}

