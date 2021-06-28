package by.ilya.catalog.facade.admin;

import by.ilya.catalog.domain.Author;
import by.ilya.catalog.domain.Contest;
import by.ilya.catalog.domain.Submission;
import by.ilya.catalog.dto.admin.AuthorDTO;
import by.ilya.catalog.dto.admin.ContestDTO;
import by.ilya.catalog.dto.admin.SubmissionDTO;
import by.ilya.catalog.mapper.AdminMapper;
import by.ilya.catalog.service.admin.AuthorServiceImpl;
import by.ilya.catalog.service.admin.ContestServiceImpl;
import by.ilya.catalog.service.admin.ManagerService;
import by.ilya.catalog.service.admin.SubGovernanceServiceImpl;
import by.ilya.catalog.service.admin.SubmissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPageFacadeImpl implements AdminPageFacade {

    private static final AdminMapper MAPPER = AdminMapper.INSTANCE;

    private ManagerService managerService;
    private SubGovernanceServiceImpl subGovernanceServiceImpl;
    private ContestServiceImpl contestServiceImpl;
    private AuthorServiceImpl authorServiceImpl;
    private SubmissionServiceImpl submissionServiceImpl;


    @Override
    public List<ContestDTO> getContestList() {
        return MAPPER.toContestListDTO(contestServiceImpl.getList());
    }

    @Override
    public ContestDTO createContest(ContestDTO contestDTO) {
        Contest contest = MAPPER.toState(contestDTO);
        return MAPPER.toDTO(contestServiceImpl.create(contest));
    }

    @Override
    public SubmissionDTO createSubmission(SubmissionDTO submissionDTO) {
        Submission submission = MAPPER.toState(submissionDTO);
        return MAPPER.toDTO(submissionServiceImpl.create(submission));
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = MAPPER.toState(authorDTO);
        return MAPPER.toDTO(authorServiceImpl.create(author));
    }

    @Autowired
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    @Autowired
    public void setSubGovernanceServiceImpl(SubGovernanceServiceImpl subGovernanceServiceImpl) {
        this.subGovernanceServiceImpl = subGovernanceServiceImpl;
    }

    @Autowired
    public void setContestServiceImpl(ContestServiceImpl contestServiceImpl) {
        this.contestServiceImpl = contestServiceImpl;
    }

    @Autowired
    public void setAuthorServiceImpl(AuthorServiceImpl authorServiceImpl) {
        this.authorServiceImpl = authorServiceImpl;
    }

    @Autowired
    public void setSubmissionServiceImpl(SubmissionServiceImpl submissionServiceImpl) {
        this.submissionServiceImpl = submissionServiceImpl;
    }
}
