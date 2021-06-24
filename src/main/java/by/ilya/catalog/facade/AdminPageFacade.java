package by.ilya.catalog.facade;

import by.ilya.catalog.domain.Manager;
import by.ilya.catalog.dto.AuthorDTO;
import by.ilya.catalog.dto.ContestDTO;
import by.ilya.catalog.dto.ManagerDTO;
import by.ilya.catalog.dto.SubGovernanceDTO;
import by.ilya.catalog.dto.SubmissionDTO;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminPageFacade {

    List<ContestDTO> getContestList();


    ContestDTO createContest(ContestDTO contestDTO);

    SubmissionDTO createSubmission(SubmissionDTO submissionDTO);

    AuthorDTO createAuthor(AuthorDTO authorDTO);

}
