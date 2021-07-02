package com.ilya.catalog.repository;

import com.ilya.catalog.domain.LinkDB;
import com.ilya.catalog.dto.admin.LinkDBDTO;
import com.ilya.catalog.dto.catalog.SubmissionCatalogDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkDBRepository extends JpaRepository<LinkDB, Long> {

    @Query("select new com.ilya.catalog.dto.admin.LinkDBDTO(link.name, link.url) " +
            "from LinkDB link left join link.submission sub " +
            "where sub.id = :id")
    List<LinkDBDTO> findLinksSubmissionId(@Param("id") long id);

}
