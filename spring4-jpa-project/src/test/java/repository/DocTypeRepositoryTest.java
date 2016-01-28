package repository;
 
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import persistence.config.DatabaseConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import entity.DocType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
public class DocTypeRepositoryTest {

  @Inject
  private DocTypeRepository repository;  
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    assertNotNull("Verifying repository instance ", repository);
  }
  
  @Test
  public void testFindAll() {
    System.out.println("Test called!");
    List<DocType> findAll = repository.findAll();
    findAll.forEach(action -> {
      System.out.println(
          String.format("Id == %s and Description == %s " , action.getDocTypeIndic() , action.getDescription()));
    });
  }

  @Test
  public void testSave() {
    System.out.println("Test called!");
    DocType docType = new DocType();
    docType.setDocTypeIndic("TT");
    docType.setDescription("Description");
    docType.setCreatedDate(new Timestamp(new Date().getTime()));
    DocType save = repository.save(docType);
      System.out.println(
          String.format("Saved Record Id == %s and Description == %s " , save.getDocTypeIndic() , save.getDescription()));
  }  
  
} 

