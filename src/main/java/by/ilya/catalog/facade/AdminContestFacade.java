package by.ilya.catalog.facade;

import by.ilya.catalog.domain.Contest;
import by.ilya.catalog.domain.StatusEnum;
import by.ilya.catalog.domain.SubGovernance;
import by.ilya.catalog.domain.Submission;
import by.ilya.catalog.dto.ContestDTO;
import by.ilya.catalog.dto.SubGovernanceDTO;
import by.ilya.catalog.mapper.CatalogMapper;
import by.ilya.catalog.service.ContestServiceImpl;
import by.ilya.catalog.service.SubGovernanceServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AdminContestFacade {

    private ContestServiceImpl contestServiceImpl;
    private SubGovernanceServiceImpl subGovernanceServiceImpl;
    private AdminSubmissionFacade adminSubmissionFacade;
    private static final CatalogMapper MAPPER = CatalogMapper.INSTANCE;

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

    public ContestDTO getById(long id) {
        return MAPPER.toDTO(contestServiceImpl.getById(id));
    }

    public List<ContestDTO> getList() {
        return MAPPER.toContestListDTO(contestServiceImpl.getList());
    }


    @Transactional
    public List<ContestDTO> delete(long id) {
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
