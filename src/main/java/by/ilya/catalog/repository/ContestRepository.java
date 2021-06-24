package by.ilya.catalog.repository;

import by.ilya.catalog.domain.Contest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends CrudRepository<Contest, Long> {
}
