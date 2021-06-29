package by.ilya.catalog.repository;

import by.ilya.catalog.domain.LinkDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkDBRepository extends JpaRepository<LinkDB, Long> {
}
