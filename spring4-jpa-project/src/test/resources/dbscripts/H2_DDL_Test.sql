/*==============================================================*/
/* Table: DOC_TYPE                                              */
/*==============================================================*/
CREATE TABLE DOC_TYPE
(
   DOC_TYPE_INDIC       VARCHAR(12) NOT NULL,
   DESCRIPTION          VARCHAR(72),
   CREATED_DATE         TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
   MODIFIED_DATE        TIMESTAMP NULL DEFAULT NULL,
   PRIMARY KEY (DOC_TYPE_INDIC)
);

/*==============================================================*/
/* Insert rows in to DOC_TYPE                                   */
/*==============================================================*/


Insert into  doc_type (DOC_TYPE_INDIC, DESCRIPTION, CREATED_DATE, MODIFIED_DATE) VALUES 
('DOC1', 'First', current_timestamp, NULL);
Insert into  doc_type (DOC_TYPE_INDIC, DESCRIPTION, CREATED_DATE, MODIFIED_DATE) VALUES 
('DOC2', 'Second', current_timestamp, NULL);


COMMIT;