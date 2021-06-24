package by.ilya.catalog.repository;

import by.ilya.catalog.domain.SubGovernance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubGovernanceRepository extends JpaRepository<SubGovernance, Long> {
}
