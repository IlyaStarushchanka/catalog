package by.ilya.catalog.facade;

import by.ilya.catalog.domain.Contest;
import by.ilya.catalog.domain.SubGovernance;
import by.ilya.catalog.domain.Submission;
import by.ilya.catalog.dto.ContestDTO;
import by.ilya.catalog.dto.SubmissionDTO;
import by.ilya.catalog.mapper.CatalogMapper;
import by.ilya.catalog.service.ContestServiceImpl;
import by.ilya.catalog.service.SubGovernanceServiceImpl;
import by.ilya.catalog.service.SubmissionServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminSubmissionFacade {

    private SubmissionServiceImpl submissionServiceImpl;
    private ContestServiceImpl contestServiceImpl;
    private static final CatalogMapper MAPPER = CatalogMapper.INSTANCE;

    @Transactional
    public SubmissionDTO create(SubmissionDTO submissionDTO) {
        Submission submission = MAPPER.toState(submissionDTO);
        Contest contest = null;
        if (submission.getContest() != null && submission.getContest().getId() != null) {
            contest = contestServiceImpl.getById(submission.getContest().getId());
            submission.setContest(contest);
        }
        submission = submissionServiceImpl.create(submission);
        if (contest != null) {
            contest.getSubmissions().add(submission);
        }
        return MAPPER.toDTO(submission);
    }

    public SubmissionDTO getById(long id) {
        return MAPPER.toDTO(submissionServiceImpl.getById(id));
    }

    public List<SubmissionDTO> getList() {
        return MAPPER.toSubmissionListDTO(submissionServiceImpl.getList());
    }


    public List<SubmissionDTO> delete(long id) {
        Submission submission = submissionServiceImpl.getById(id);
        if (submission != null) {
            submissionServiceImpl.delete(id);
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
    public void setSubmissionServiceImpl(SubmissionServiceImpl submissionServiceImpl) {
        this.submissionServiceImpl = submissionServiceImpl;
    }


}
