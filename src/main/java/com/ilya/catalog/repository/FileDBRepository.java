package com.ilya.catalog.repository;

import com.ilya.catalog.domain.FileDB;
import com.ilya.catalog.dto.admin.LinkDBDTO;
import com.ilya.catalog.dto.admin.ResponseFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

    @Query("select new com.ilya.catalog.dto.admin.ResponseFile(file.name, file.type, file.id) " +
            "from FileDB file left join file.submission sub " +
            "where sub.id = :id")
    List<ResponseFile> findFilesSubmissionId(@Param("id") long id);

}
