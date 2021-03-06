package com.ilya.catalog.facade.admin;

import com.ilya.catalog.domain.Contest;
import com.ilya.catalog.domain.SubGovernance;
import com.ilya.catalog.domain.Submission;
import com.ilya.catalog.dto.admin.ContestDTO;
import com.ilya.catalog.dto.admin.SmallContestAdminDTO;
import com.ilya.catalog.mapper.AdminMapper;
import com.ilya.catalog.service.admin.ContestServiceImpl;
import com.ilya.catalog.service.admin.SubGovernanceServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminContestFacade {

    private ContestServiceImpl contestServiceImpl;
    private SubGovernanceServiceImpl subGovernanceServiceImpl;
    private AdminSubmissionFacade adminSubmissionFacade;
    private static final AdminMapper MAPPER = AdminMapper.INSTANCE;

    @Transactional
    public ContestDTO create(ContestDTO contestDTO) {
        Contest contest = MAPPER.toState(contestDTO);
        SubGovernance subGovernance = null;
        if (contest.getSubGovernance() != null && contest.getSubGovernance().getId() != null) {
            subGovernance = subGovernanceServiceImpl.getById(contest.getSubGovernance().getId());
            contest.setSubGovernance(subGovernance);
        }
        contest = contestServiceImpl.create(contest);
        if (subGovernance != null) {
            subGovernance.getContests().add(contest);
        }
        return MAPPER.toDTO(contest);
    }

    @Transactional
    public ContestDTO getById(long id) {
        return MAPPER.toDTO(contestServiceImpl.getById(id));
    }

    @Transactional
    public List<SmallContestAdminDTO> getList() {
        return contestServiceImpl.findAdminContests();
    }


    @Transactional
    public List<SmallContestAdminDTO> delete(long id) {
        Contest contest = contestServiceImpl.getById(id);
        if (contest != null) {
            contest.getSubGovernance().getContests().remove(contest);
            List<Long> ids = contest.getSubmissions().stream().map(Submission::getId).collect(Collectors.toList());
            adminSubmissionFacade.deleteAll(ids);
            contestServiceImpl.delete(id);
        }
        return getList();
    }

    @Transactional
    public List<ContestDTO> edit(ContestDTO contestDTO) throws NotFoundException {
        Contest contest = contestServiceImpl.getById(contestDTO.getId());
        Contest newContestData = MAPPER.toState(contestDTO);
        if (contest == null){
            throw new NotFoundException("");
        }
        SubGovernance newSubGovernance = null;
        if (contestDTO.getSubGovernance() != null) {
            newSubGovernance = subGovernanceServiceImpl.getById(contestDTO.getSubGovernance().getId());
        }
        contestServiceImpl.edit(newSubGovernance, contest, newContestData);
        return MAPPER.toContestListDTO(contestServiceImpl.getList());
    }



    @Autowired
    public void setContestServiceImpl(ContestServiceImpl contestServiceImpl) {
        this.contestServiceImpl = contestServiceImpl;
    }

    @Autowired
    public void setSubGovernanceServiceImpl(SubGovernanceServiceImpl subGovernanceServiceImpl) {
        this.subGovernanceServiceImpl = subGovernanceServiceImpl;
    }

    @Autowired
    public void setAdminSubmissionFacade(AdminSubmissionFacade adminSubmissionFacade) {
        this.adminSubmissionFacade = adminSubmissionFacade;
    }
}
