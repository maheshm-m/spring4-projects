package entity;

import java.io.Serializable;
import java.sql.Timestamp;

//import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
 
@Entity 
//@Cacheable
@Table(name = "doc_type")
@NamedQuery(name = "DocType.findAll", query = "SELECT d FROM DocType d")
public class DocType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Id
    @Column(name = "DOC_TYPE_INDIC")
    private String id;

    @Column(name = "MODIFIED_DATE")
    private Timestamp modifiedDate;

    /**
     * DocType()
     */
    public DocType() {
        // Intentionally left blank.
    }
   
    /**
     * Get DocType createdDate
     * @return Timestamp createdDate
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * Set DocType createdDate
     * @param createdDate Timestamp
     */ 
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Get DocType description
     * @return String description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set DocType description
     * @param description String
     */ 
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get DocType DocTypeIndic id
     * @return id String DocTypeIndic
     */
    public String getDocTypeIndic() {
        return id;
    }

    /**
     * Set DocType DocTypeIndic id
     * @param id String DocTypeIndic
     */ 
    public void setDocTypeIndic(String id) {
        this.id = id;
    }

    /**
     * Get DocType modifiedDate
     * @return Timestamp modifiedDate
     */
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Set DocType modifiedDate
     * @param modifiedDate Timestamp
     */ 
    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

}