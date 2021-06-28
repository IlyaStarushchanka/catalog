package by.ilya.catalog.facade.admin;

import by.ilya.catalog.domain.SubGovernance;
import by.ilya.catalog.dto.admin.SubGovernanceDTO;
import by.ilya.catalog.mapper.AdminMapper;
import by.ilya.catalog.service.admin.SubGovernanceServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminSubGovernanceFacade {

    private SubGovernanceServiceImpl subGovernanceServiceImpl;
    private static final AdminMapper MAPPER = AdminMapper.INSTANCE;

    public SubGovernanceDTO create(SubGovernanceDTO subGovernanceDTO) {
        SubGovernance subGovernance = MAPPER.toState(subGovernanceDTO);
        return MAPPER.toDTO(subGovernanceServiceImpl.create(subGovernance));
    }

    public SubGovernanceDTO getById(long id) {
        return MAPPER.toDTO(subGovernanceServiceImpl.getById(id));
    }

    public List<SubGovernanceDTO> getList() {
        return MAPPER.toSubGovernancesListDTO(subGovernanceServiceImpl.getList());
    }


    public List<SubGovernanceDTO> delete(long id) {
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
