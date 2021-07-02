package com.ilya.catalog.repository;

import com.ilya.catalog.domain.SubGovernance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubGovernanceRepository extends JpaRepository<SubGovernance, Long> {
}
