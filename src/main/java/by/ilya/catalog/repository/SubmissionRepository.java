package by.ilya.catalog.repository;

import by.ilya.catalog.domain.Submission;
import by.ilya.catalog.dto.admin.SubmissionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    /*@Query("select c.name from Contest c where c.name like %:search%")
    SubmissionDTO findForCatalogById(long id);*/

}
