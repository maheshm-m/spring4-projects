package repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
 
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    /**
     * Base save.
     * 
     * @param persisted 
     *            S
     * @param <S>
     *            <S>
     * @return S <S extends T>
     */
    <S extends T> S save(S persisted);

    /**
     * Base delete.
     * 
     * @param arg0
     *            T
     */
    void delete(T arg0);

    /**
     * Base find by id.
     * 
     * @param id
     *            ID
     * @return T
     */
    T findById(ID id);

    /**
     * Base Find All.
     * 
     * @return List<T>
     */
    List<T> findAll();

    /**
     * Base Find All (Sorted).
     * 
     * @param arg0
     *            Sort
     * @return List<T>
     */
    List<T> findAll(Sort arg0);

    /**
     * Base Find All (Paged).
     * 
     * @param pageable
     *            Pageable
     * @return Page<T>
     */
    Page<T> findAll(Pageable pageable);

    /**
     * Find all entities using criteria query specification.
     * 
     * @param spec the Specification
     * @return  List<T> 
     */
    List<T> findAll(Specification<T> spec);

    /**
     * Find all entities using criteria query specification and return as
     * pageable objects.
     * 
     * @param spec the Specification
     * @param pageable the Pageable
     * @return Page<T>
     */
    Page<T> findAll(Specification<T> spec, Pageable pageable);
}
