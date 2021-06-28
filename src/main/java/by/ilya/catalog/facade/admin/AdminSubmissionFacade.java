package by.ilya.catalog.facade.admin;

import by.ilya.catalog.domain.Author;
import by.ilya.catalog.domain.Contest;
import by.ilya.catalog.domain.Submission;
import by.ilya.catalog.dto.admin.SubmissionDTO;
import by.ilya.catalog.mapper.AdminMapper;
import by.ilya.catalog.service.admin.AuthorServiceImpl;
import by.ilya.catalog.service.admin.ContestServiceImpl;
import by.ilya.catalog.service.admin.SubmissionServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
public class AdminSubmissionFacade {

    private SubmissionServiceImpl submissionServiceImpl;
    private ContestServiceImpl contestServiceImpl;
    private AuthorServiceImpl authorServiceImpl;
    private static final AdminMapper MAPPER = AdminMapper.INSTANCE;

    @Transactional
    public void create(SubmissionDTO submissionDTO) {
        Submission submission = MAPPER.toState(submissionDTO);
        Contest contest = null;
        if (submission.getContest() != null && submission.getContest().getId() != null) {
            contest = contestServiceImpl.getById(submission.getContest().getId());
            submission.setContest(contest);
        }
        Author author = null;
        if (submission.getAuthor() != null && submission.getAuthor().getId() != null) {
            author = authorServiceImpl.getById(submission.getAuthor().getId());
            submission.setAuthor(author);
        }
        submission.setAuthorFreeTonAddress(submissionDTO.getAuthor().getChosedFreeTonAddress());
        submission = submissionServiceImpl.create(submission);
        if (contest != null) {
            contest.getSubmissions().add(submission);
        }
        if (author != null) {
            author.getSubmissions().add(submission);
        }
    }

    @Transactional
    public SubmissionDTO getById(long id) {
        return MAPPER.toDTO(submissionServiceImpl.getById(id));
    }

    public List<SubmissionDTO> getList() {
        return MAPPER.toSubmissionListDTO(new HashSet<>(submissionServiceImpl.getList()));
    }


    @Transactional
    public void delete(long id) {
        Submission submission = submissionServiceImpl.getById(id);
        if (submission != null) {
            Author author = submission.getAuthor();
            author.getSubmissions().remove(submission);
            Contest contest = submission.getContest();
            contest.getSubmissions().remove(submission);
            submissionServiceImpl.delete(id);
        }
    }

    @Transactional
    public void deleteAll(List<Long> ids) {
        for (Long id : ids) {
            Submission submission = submissionServiceImpl.getById(id);
            if (submission != null) {
                Author author = submission.getAuthor();
                author.getSubmissions().remove(submission);
                Contest contest = submission.getContest();
                contest.getSubmissions().remove(submission);
                submissionServiceImpl.delete(id);
            }
        }
    }

    @Transactional
    public void edit(SubmissionDTO submissionDTO) throws NotFoundException {
        Submission submission = submissionServiceImpl.getById(submissionDTO.getId());
        Submission newSubmissionData = MAPPER.toState(submissionDTO);
        if (submission == null){
            throw new NotFoundException("");
        }
        Contest newContest = null;
        if (submissionDTO.getContest() != null) {
            newContest = contestServiceImpl.getById(submission.getContest().getId());
        }
        Author newAuthor = null;
        if (submissionDTO.getAuthor() != null) {
            newAuthor = authorServiceImpl.getById(submission.getAuthor().getId());
        }
        submissionServiceImpl.edit(newContest,newAuthor, submission, newSubmissionData);
    }

    @Autowired
    public void setSubmissionServiceImpl(SubmissionServiceImpl submissionServiceImpl) {
        this.submissionServiceImpl = submissionServiceImpl;
    }

    @Autowired
    public void setContestServiceImpl(ContestServiceImpl contestServiceImpl) {
        this.contestServiceImpl = contestServiceImpl;
    }

    @Autowired
    public void setAuthorServiceImpl(AuthorServiceImpl authorServiceImpl) {
        this.authorServiceImpl = authorServiceImpl;
    }
}
