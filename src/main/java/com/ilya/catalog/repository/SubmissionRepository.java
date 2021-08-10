package com.ilya.catalog.repository;

import com.ilya.catalog.domain.Submission;
import com.ilya.catalog.dto.admin.SmallSubmissionAdminDTO;
import com.ilya.catalog.dto.catalog.ContestCatalogDTO;
import com.ilya.catalog.dto.catalog.SmallSubmissionCatalogDTO;
import com.ilya.catalog.dto.catalog.SubmissionCatalogDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    @Query("select new com.ilya.catalog.dto.catalog.SmallSubmissionCatalogDTO(COUNT(links), COUNT(files), s.id, s.number, " +
            "s.rate, s.place, s.prize, author.id, author.freetonForumNickname, s.authorFreeTonAddress) " +
            "from Submission s left join s.author author left join s.files files left join s.links links " +
            "where s.contest.id = :contestId " +
            "group by s.id, s.number, s.rate, s.place, s.prize, author.id, author.freetonForumNickname, " +
            "s.authorFreeTonAddress")
    List<SmallSubmissionCatalogDTO> findSubmissionsWithContestId(@Param("contestId") long contestId);


    @Query("select new com.ilya.catalog.dto.catalog.SubmissionCatalogDTO(s.id, s.smallDescription, s.bigDescription, " +
            "s.number, s.rate, s.place, s.prize, s.authorFreeTonAddress, contest.id, contest.name, subGov.id, " +
            "subGov.name, author.id, author.freetonForumNickname, s.statisticsLink) " +
            "from Submission s left join s.contest contest left join s.author author left join contest.subGovernance subGov " +
            "where s.id = :id")
    SubmissionCatalogDTO findBySubmissionId(@Param("id") long id);


    @Query("select new com.ilya.catalog.dto.admin.SmallSubmissionAdminDTO(s.id, s.number, " +
            "s.rate, s.place, s.prize, author.id, author.freetonForumNickname, s.authorFreeTonAddress, c.name) " +
            "from Submission s left join s.author author left join s.contest c ")
    List<SmallSubmissionAdminDTO> findAdminSubmissions();
}
