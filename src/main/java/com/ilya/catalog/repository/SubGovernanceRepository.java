package com.ilya.catalog.repository;

import com.ilya.catalog.domain.SubGovernance;
import com.ilya.catalog.dto.admin.SmallSubGovernanceAdminDTO;
import com.ilya.catalog.dto.admin.SmallSubmissionAdminDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubGovernanceRepository extends JpaRepository<SubGovernance, Long> {

    @Query("select new com.ilya.catalog.dto.admin.SmallSubGovernanceAdminDTO(s.id, s.name) from SubGovernance s")
    List<SmallSubGovernanceAdminDTO> findAdminSubGovernances();
}
