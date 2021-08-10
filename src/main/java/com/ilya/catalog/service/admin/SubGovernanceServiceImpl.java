package com.ilya.catalog.service.admin;

import com.ilya.catalog.domain.Contest;
import com.ilya.catalog.domain.SubGovernance;
import com.ilya.catalog.dto.admin.SmallSubGovernanceAdminDTO;
import com.ilya.catalog.dto.admin.SmallSubmissionAdminDTO;
import com.ilya.catalog.repository.SubGovernanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubGovernanceServiceImpl implements CrudService<SubGovernance> {

    private SubGovernanceRepository subGovernanceRepository;

    @Transactional
    public SubGovernance create(SubGovernance newSubGovernance) {
        for (Contest contest : newSubGovernance.getContests()){
            contest.setSubGovernance(newSubGovernance);
        }
        return subGovernanceRepository.save(newSubGovernance);
    }

    @Override
    public List<SubGovernance> getList() {
        return subGovernanceRepository.findAll();
    }

    public List<SmallSubGovernanceAdminDTO> findAdminSubmissions() {
        return subGovernanceRepository.findAdminSubGovernances();
    }

    @Override
    public SubGovernance getById(long id) {
        return subGovernanceRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(long id) {
        subGovernanceRepository.deleteById(id);
    }

    @Autowired
    public void setSubGovernanceRepository(SubGovernanceRepository subGovernanceRepository) {
        this.subGovernanceRepository = subGovernanceRepository;
    }
}
