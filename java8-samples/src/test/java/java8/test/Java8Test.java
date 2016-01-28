package java8.test;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.counting;
import static java.util.function.Function.identity;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java8.model.MatchTable;

/**
 * @author 7827983
 *
 */
public class Java8Test {

  private static final Logger LOGGER = Logger.getLogger("Java8Test");
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  @Test
  @Ignore
  public void testListStream() {
    String fileName = "src/test/resources/data.txt";
    try {
      List<String> readAllLines = Files.readAllLines(Paths.get(fileName));
      List <MatchTable> listMatchTable = new ArrayList<>();
      readAllLines.forEach(line -> {
        //System.out.println(format(" The List content %s-- ", line ));
        MatchTable matchTable = new MatchTable();
        matchTable.setLine(line);
        listMatchTable.add(matchTable);
      });
      
      List <String> eachLine = listMatchTable.stream().map(MatchTable::getLine).collect(Collectors.toList());
      
      eachLine.forEach(System.out::println);
      
    } catch (IOException e) {
      String errorMsg = String.format("Failed to read file - %s", fileName);
      LOGGER.warning(errorMsg);
    }
  }
  

  @Test
  @Ignore
  public void testNumericRange() {
    IntStream.rangeClosed(1, 5).forEach(action -> System.out.println(action)); // Prints 1...5
    IntStream.range(1, 5).forEach(action -> System.out.println(action)); // Prints 1...4
  }
  
  @Test
  @Ignore
  public void testBuildStream() throws Exception {
    String [] stringArrays = new String[] {"Prabha","Mahesh", "Manu", "Tanu", };
    
    //Arrays.stream(stringArrays).forEach(System.out::println);
    //Arrays.stream(stringArrays).forEachOrdered(System.out::println);
    BufferedReader newBufferedReader = Files.newBufferedReader(Paths.get("src/test/resources/data.txt"));
    //newBufferedReader.lines().filter(line-> line.contains("Arsenal")).forEach(System.out::println);
    
    newBufferedReader.lines().forEach(System.out::println);
    System.out.println("================");
    newBufferedReader = Files.newBufferedReader(Paths.get("src/test/resources/data2.txt"));
    newBufferedReader.lines().parallel().forEach(System.out::println);
  }
  
  @Test
  public void testMatchInteger() throws Exception {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 7, 8);
    numbers = Arrays.asList(7, 7, 8);
    boolean allMatch = numbers.stream().allMatch(p -> p.intValue() >= 7);
    
    numbers = Arrays.asList(7, 7, 8);
    allMatch = numbers.stream().anyMatch(p -> p.intValue() == 7);    
    
    //System.out.println("Value is : " + numbers.contains(7));
    //System.out.println("Value is : " + allMatch);
    
    //numbers.stream().distinct().forEach(System.out::println);
    
    //System.out.println("Value is : " + numbers.stream().filter(p -> p.intValue() == 7).count());
    
    numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 7,0,-1,-2, 8);
    
    //numbers = Arrays.asList(0,0);
    System.out.println("Min Value is : " + numbers.stream().min(Integer::compare));
    List<String> strings = Arrays.asList("Oracle","Java","Magazine");
    
    strings.stream().map(String::length).collect(Collectors.toList()).forEach(System.out::println);;
    
    strings.stream().collect(Collectors.toMap(s -> s, String::length)).forEach((k,v)-> System.out.println(String.format("Key %s and Value %s",k,v)));
    
  }
  
  @Test
  //@Ignore
  public void testMap() throws Exception {
    //List<String> readAllLines = Files.readAllLines(Paths.get("src/test/resources/data3.txt"));
    //readAllLines.stream().skip(1).map(s -> s.split("\\s+"));
    List <Integer> numbers = Arrays.asList(1, 4, 5, 0);
    Optional<Integer> reduce = numbers.stream().reduce((a,b) -> (a+b));
    System.out.println("Reduce Value is : " + reduce.get());
    
    Integer reduce2 = numbers.stream().reduce(100, (a,b) -> (a+b));
    System.out.println("Reduce Value is : " + reduce2);
    
    
    Optional<Integer> reduce3 = numbers.stream().reduce(Integer::max);
    System.out.println("Reduce Max Value is : " + reduce3.get());
    
    Optional<Integer> reduce4 = numbers.stream().min(Integer::compare);
    System.out.println("Reduce Min Value is : " + reduce4.get());    
    
    List <String> numbers1 = Arrays.asList("1", "4", "5", "0"); 
    int sum = numbers1.stream().mapToInt(Integer::parseInt).sum();
    System.out.println("Sum Value is : " + sum);
    
    Stream<Integer> numberStream = Stream.iterate(20, n -> n - 1).limit(5);
    
    numberStream.forEach(System.out::println);
    
  }
  
  @Test
  @Ignore
  public void testFlatMap() throws Exception {
    Stream<String> strings = Stream.of("Java","Magazine","is","the","best");
    
    Map<String, Long> collect = strings.map(m -> m.split("")).flatMap(Arrays::stream)
      .collect(groupingBy(identity(),counting()));
    
//    System.out.println( collect);
//    
//    collect.forEach((k,v) -> {
//      System.out.println( " Key : " +  k + " Value"  + v);
//    });
    
    
    Stream<Integer> ints = Stream.of(6,5);
    
    
    OptionalDouble average = ints.mapToInt(a -> a).average();
//    System.out.println( "Average " + Math.ceil(average.getAsDouble()));
//    System.out.println( "Average " + Math.floor(average.getAsDouble()));
//    System.out.println( "Average " + Math.floor(average.getAsDouble()));
    Double d = new Double(1.23);
    int i = d.intValue();
    //System.out.println( "Average " + i);
    
    List <Integer> numbers = Arrays.asList(1, 4, 5, 0);
    numbers.forEach((Integer action) -> System.out.println(action));
    Optional<Integer> reduce = numbers.stream().reduce((a,b) -> (a+b));
    System.out.println( "reduce " + reduce.get());
  }
  
  @Test
  public void testPredicate() {
    Predicate predicate = (s) -> s.equals("Mahesh1");
    
    System.out.println("Printing Predicate --- " + predicate.test("Mahesh"));
    

    BiFunction<Integer, Integer, Integer> biFunction = (Integer a , Integer b) -> (a  + b) ; 
    
    System.out.println("BiFunction BiFunction --- " + biFunction.apply(4, 5));
    
    Function<Integer, Integer> function = (a) -> (a + 5);  
    
    System.out.println("Function function --- " + function.apply(10));
    
    String join = String.join("==", "A" , "B", "C");
    
    System.out.println("String.join join --- " + join);
  }
}

