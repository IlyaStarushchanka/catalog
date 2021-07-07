package com.ilya.catalog.repository;

import com.ilya.catalog.domain.Contest;
import com.ilya.catalog.dto.catalog.ContestCatalogDTO;
import com.ilya.catalog.dto.catalog.SmallContestCatalogDTO;
import com.ilya.catalog.dto.catalog.SearchNamesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {

    @Query("select new com.ilya.catalog.dto.catalog.SmallContestCatalogDTO(c.id, c.name, c.submissionFrom, c.submissionTo, " +
            "c.prizeFund, subg.id, subg.name ,COUNT(subs)) from Contest c left join c.subGovernance subg left join c.submissions subs " +
            "where lower(c.name) like lower(concat('%',:name,'%')) " +
            "group by c.id, c.name, c.submissionFrom, c.submissionTo, c.prizeFund, subg.name, subg.id")
    List<SmallContestCatalogDTO> findContests(@Param("name") String name);

    @Query("select new com.ilya.catalog.dto.catalog.SearchNamesDTO(c.id, c.name) from Contest c where lower(c.name) like lower(concat('%',:search,'%'))")
    List<SearchNamesDTO> getContestNames(@Param("search") String search);

    @Query("select new com.ilya.catalog.dto.catalog.SmallContestCatalogDTO(c.id, c.name, c.submissionFrom, c.submissionTo, " +
            "c.prizeFund, subg.id, subg.name ,COUNT(subs)) from Contest c left join c.subGovernance subg " +
            "left join c.submissions subs group by c.id, c.name, c.submissionFrom, c.submissionTo, c.prizeFund, " +
            "subg.name, subg.id")
    List<SmallContestCatalogDTO> findContests();

    @Query("select new com.ilya.catalog.dto.catalog.ContestCatalogDTO(c.id, c.name, c.smallDescription, c.bigDescription, " +
            "c.submissionFrom, c.submissionTo, c.votingFrom, c.votingTo, c.prizeFund, subg.id, subg.name) " +
            "from Contest c left join c.subGovernance subg where c.id = :id")
    ContestCatalogDTO findByContestId(@Param("id") long id);

    @Query("select new com.ilya.catalog.dto.catalog.SmallContestCatalogDTO(c.id, c.name, c.submissionFrom, c.submissionTo, " +
            "c.prizeFund, subg.id, subg.name ,COUNT(subs)) from Contest c left join c.subGovernance subg " +
            "left join c.submissions subs WHERE " +
            "((:ids) is null or c.subGovernance.id IN (:ids)) " +
            "AND ( :prizeFrom is null or c.prizeFund >= :prizeFrom ) AND (:prizeTo is null or c.prizeFund <= :prizeTo) " +
            "AND (:winnersFrom is null or size(c.submissions) >= :winnersFrom) " +
            "AND (:winnersTo is null or size(c.submissions) <= :winnersTo) " +
            "AND ( :search is null or lower(c.name) like lower(concat('%',:search,'%')) ) " +
            "group by c.id, c.name, c.submissionFrom, c.submissionTo, c.prizeFund, subg.name, subg.id")
    List<SmallContestCatalogDTO> getFilteredList(
            @Param("ids") Collection<Long> ids,@Param("prizeFrom") Integer prizeFrom, @Param("prizeTo")  Integer prizeTo,
            @Param("winnersFrom") Integer winnersFrom, @Param("winnersTo")  Integer winnersTo, @Param("search") String search);

    /*@Query(value = "SELECT c FROM Contest c WHERE ((:ids) is null or c.subGovernance.id IN (:ids)) " +
            "AND ( :prizeFrom is null or c.prizeFund >= :prizeFrom ) AND (:prizeTo is null or c.prizeFund <= :prizeTo) " +
            "AND (:winnersFrom is null or size(c.submissions) >= :winnersFrom) " +
            "AND (:winnersTo is null or size(c.submissions) <= :winnersTo)")
    List<Contest> getFilteredList(
            @Param("ids") Collection<Long> ids,@Param("prizeFrom") Integer prizeFrom, @Param("prizeTo")  Integer prizeTo,
            @Param("winnersFrom") Integer winnersFrom, @Param("winnersTo")  Integer winnersTo);*/




}
