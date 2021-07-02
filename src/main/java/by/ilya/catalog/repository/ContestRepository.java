package by.ilya.catalog.repository;

import by.ilya.catalog.domain.Contest;
import by.ilya.catalog.domain.SubGovernance;
import by.ilya.catalog.dto.catalog.SmallContestCatalogDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {

    List<Contest> findByNameContaining(String name);

    @Query("select c.name from Contest c where c.name like %:search%")
    List<String> getContestNames(@Param("search") String search);

    @Query("select c.id, c.name, c.submissionFrom, c.submissionTo, c.prizeFund, COUNT(c.submissions) as winnersCount, c.subGovernance.name, c.subGovernance.id from Contest c")
    List<SmallContestCatalogDTO> findContests();

    @Query(value = "SELECT c FROM Contest c WHERE ((:ids) is null or c.subGovernance.id IN (:ids)) " +
            "AND ( :prizeFrom is null or c.prizeFund >= :prizeFrom ) AND (:prizeTo is null or c.prizeFund <= :prizeTo) " +
            "AND (:winnersFrom is null or size(c.submissions) >= :winnersFrom) " +
            "AND (:winnersTo is null or size(c.submissions) <= :winnersTo)")
    List<Contest> getFilteredList(
            @Param("ids") Collection<Long> ids,@Param("prizeFrom") Integer prizeFrom, @Param("prizeTo")  Integer prizeTo,
            @Param("winnersFrom") Integer winnersFrom, @Param("winnersTo")  Integer winnersTo);

    @Query(value = "SELECT c FROM Contest c WHERE ((:ids) is null or c.subGovernance.id IN (:ids)) " +
            "AND ( :prizeFrom is null or c.prizeFund >= :prizeFrom ) AND (:prizeTo is null or c.prizeFund <= :prizeTo) " +
            "AND (:winnersFrom is null or size(c.submissions) >= :winnersFrom) " +
            "AND (:winnersTo is null or size(c.submissions) <= :winnersTo) ORDER BY c.id DESC")
    List<Contest> getFilteredListDESC(
            @Param("ids") Collection<Long> ids,@Param("prizeFrom") Integer prizeFrom, @Param("prizeTo")  Integer prizeTo,
            @Param("winnersFrom") Integer winnersFrom, @Param("winnersTo")  Integer winnersTo);



}
