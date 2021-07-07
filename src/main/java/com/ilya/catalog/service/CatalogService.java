package com.ilya.catalog.service;

import com.ilya.catalog.domain.SubGovernance;
import com.ilya.catalog.dto.admin.LinkDBDTO;
import com.ilya.catalog.dto.admin.ResponseFile;
import com.ilya.catalog.dto.catalog.ContestCatalogDTO;
import com.ilya.catalog.dto.catalog.FilterEntity;
import com.ilya.catalog.dto.catalog.SmallContestCatalogDTO;
import com.ilya.catalog.dto.catalog.SmallSubmissionCatalogDTO;
import com.ilya.catalog.dto.catalog.SubmissionCatalogDTO;
import com.ilya.catalog.repository.ContestRepository;
import com.ilya.catalog.repository.FileDBRepository;
import com.ilya.catalog.repository.LinkDBRepository;
import com.ilya.catalog.repository.SubGovernanceRepository;
import com.ilya.catalog.repository.SubmissionRepository;
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
    private LinkDBRepository linkDBRepository;
    @Autowired
    private FileDBRepository fileDBRepository;
    @Autowired
    private SubGovernanceRepository subGovernanceRepository;

    public List<SmallContestCatalogDTO> getContests(){
        return contestRepository.findContests();
    }

    public SubmissionCatalogDTO getSubmissionById(long id){
        SubmissionCatalogDTO submissionCatalogDTO = submissionRepository.findBySubmissionId(id);
        List<LinkDBDTO> links = linkDBRepository.findLinksSubmissionId(id);
        List<ResponseFile> files = fileDBRepository.findFilesSubmissionId(id);
        submissionCatalogDTO.setFiles(files);
        submissionCatalogDTO.setLinks(links);
        return submissionCatalogDTO;
    }

    public List<SubGovernance> getSubGovernanceList(){
        return subGovernanceRepository.findAll();
    }

    public ContestCatalogDTO getContestById(long id){
        ContestCatalogDTO contestCatalogDTO = contestRepository.findByContestId(id);
        List<SmallSubmissionCatalogDTO> submissions = submissionRepository.findSubmissionsWithContestId(id);
        contestCatalogDTO.setSubmissions(submissions);
        return contestCatalogDTO;
    }

    public List<SmallContestCatalogDTO> getContestByContainingName(String name){
        return contestRepository.findContests(name);
    }

    public List<String> getContestNames(String search){
        return contestRepository.getContestNames(search);
    }

    public List<SmallContestCatalogDTO> getFilteredContests(FilterEntity filterEntity) {
        return contestRepository.getFilteredList(filterEntity.getSubGovesIds(),
                filterEntity.getPrizeFundFrom(), filterEntity.getPrizeFundTo(), filterEntity.getWinnersFrom(),
                filterEntity.getWinnersTo(), filterEntity.getSearch());

    }
}
