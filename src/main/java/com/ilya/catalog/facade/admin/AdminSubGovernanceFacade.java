package com.ilya.catalog.facade.admin;

import com.ilya.catalog.domain.SubGovernance;
import com.ilya.catalog.dto.admin.SmallSubGovernanceAdminDTO;
import com.ilya.catalog.dto.admin.SubGovernanceDTO;
import com.ilya.catalog.mapper.AdminMapper;
import com.ilya.catalog.service.admin.SubGovernanceServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminSubGovernanceFacade {

    private SubGovernanceServiceImpl subGovernanceServiceImpl;
    private static final AdminMapper MAPPER = AdminMapper.INSTANCE;

    @Transactional
    public SubGovernanceDTO create(SubGovernanceDTO subGovernanceDTO) {
        SubGovernance subGovernance = MAPPER.toState(subGovernanceDTO);
        return MAPPER.toDTO(subGovernanceServiceImpl.create(subGovernance));
    }

    @Transactional
    public SubGovernanceDTO getById(long id) {
        return MAPPER.toDTO(subGovernanceServiceImpl.getById(id));
    }

    @Transactional
    public List<SmallSubGovernanceAdminDTO> getList() {
        return subGovernanceServiceImpl.findAdminSubmissions();
    }


    @Transactional
    public List<SmallSubGovernanceAdminDTO> delete(long id) {
        SubGovernance subGovernance = subGovernanceServiceImpl.getById(id);
        if (subGovernance != null) {
            subGovernanceServiceImpl.delete(id);
        }
        return getList();
    }

    @Transactional
    public List<SubGovernanceDTO> edit(SubGovernanceDTO subGovernance) throws NotFoundException {
        SubGovernance existedSubGovernance = subGovernanceServiceImpl.getById(subGovernance.getId());
        if (existedSubGovernance == null){
            throw new NotFoundException("");
        }
        existedSubGovernance.setName(subGovernance.getName());
        return MAPPER.toSubGovernancesListDTO(subGovernanceServiceImpl.getList());
    }

    @Autowired
    public void setSubGovernanceServiceImpl(SubGovernanceServiceImpl subGovernanceServiceImpl) {
        this.subGovernanceServiceImpl = subGovernanceServiceImpl;
    }
}
