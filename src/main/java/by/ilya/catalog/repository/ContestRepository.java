package by.ilya.catalog.repository;

import by.ilya.catalog.domain.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {

    List<Contest> findByNameContaining(String name);

    @Query("select c.name from Contest c")
    List<String> getContestNames();
}
