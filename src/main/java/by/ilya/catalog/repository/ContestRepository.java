package by.ilya.catalog.repository;

import by.ilya.catalog.domain.Contest;
import by.ilya.catalog.domain.SubGovernance;
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

    @Query("select c.name from Contest c")
    List<String> getContestNames();

    @Query(value = "SELECT c FROM Contest c WHERE c.subGovernance.id IN :ids")
    List<Contest> findContestsBySubGovIdsList(@Param("ids") Collection<String> ids);

    @Query(value = "SELECT c FROM Contest c WHERE c.subGovernance.id IN :ids AND c.prizeFund >= :prizeFrom ")
    List<Contest> findContestsBySubGovIdsAndPrizeFromList(@Param("ids") Collection<String> ids,@Param("prizeFrom") int prizeFrom);

    @Query(value = "SELECT c FROM Contest c WHERE ((:ids) is null or c.subGovernance.id IN (:ids)) AND ( :prizeFrom is null or c.prizeFund >= :prizeFrom ) AND (:prizeTo is null or c.prizeFund <= :prizeTo)")
    List<Contest> findContestsBySubGovIdsAndPrizeFromAndToList(@Param("ids") Collection<Long> ids,@Param("prizeFrom") Integer prizeFrom, @Param("prizeTo")  Integer prizeTo);

    @Query(value = "SELECT c FROM Contest c WHERE c.subGovernance.id IN :ids AND c.prizeFund <= :prizeTo")
    List<Contest> findContestsBySubGovIdsAndPrizeToList(@Param("ids") Collection<String> ids,@Param("prizeTo")  int prizeTo);

    @Query(value = "SELECT c FROM Contest c WHERE c.prizeFund >= :prizeFrom ")
    List<Contest> findContestsByPrizeFromList(@Param("prizeFrom") int prizeFrom);

    @Query(value = "SELECT c FROM Contest c WHERE c.prizeFund >= :prizeFrom AND c.prizeFund <= :prizeTo")
    List<Contest> findContestsByPrizeFromAndToList(@Param("prizeFrom") int prizeFrom, @Param("prizeTo") int prizeTo);

    @Query(value = "SELECT c FROM Contest c WHERE c.prizeFund <= :prizeTo")
    List<Contest> findContestsPrizeToList(@Param("prizeTo") int prizeTo);


}
