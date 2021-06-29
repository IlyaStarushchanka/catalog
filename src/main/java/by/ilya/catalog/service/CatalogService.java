package by.ilya.catalog.service;

import by.ilya.catalog.domain.Contest;
import by.ilya.catalog.domain.SubGovernance;
import by.ilya.catalog.domain.Submission;
import by.ilya.catalog.repository.ContestRepository;
import by.ilya.catalog.repository.SubGovernanceRepository;
import by.ilya.catalog.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    @Autowired
    private ContestRepository contestRepository;
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private SubGovernanceRepository subGovernanceRepository;

    public List<Contest> getContests(){
        return contestRepository.findAll();
    }

    public Submission getSubmissionById(long id){
        return submissionRepository.findById(id).orElse(null);
    }

    public List<SubGovernance> getSubGovernanceList(){
        return subGovernanceRepository.findAll();
    }

    public Contest getContestById(long id){
        return contestRepository.findById(id).orElse(null);
    }
}
