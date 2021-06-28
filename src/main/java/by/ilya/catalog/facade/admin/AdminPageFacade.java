package by.ilya.catalog.facade.admin;

import by.ilya.catalog.dto.admin.AuthorDTO;
import by.ilya.catalog.dto.admin.ContestDTO;
import by.ilya.catalog.dto.admin.SubmissionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminPageFacade {

    List<ContestDTO> getContestList();


    ContestDTO createContest(ContestDTO contestDTO);

    SubmissionDTO createSubmission(SubmissionDTO submissionDTO);

    AuthorDTO createAuthor(AuthorDTO authorDTO);

}
