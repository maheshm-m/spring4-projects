package repository;

import org.springframework.stereotype.Repository;
import entity.DocType;
import repository.BaseRepository;

@Repository
public interface DocTypeRepository extends BaseRepository<DocType, String> {

}
  