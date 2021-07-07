package com.ilya.catalog.facade;

import com.ilya.catalog.dto.catalog.ContestCatalogDTO;
import com.ilya.catalog.dto.catalog.FilterEntity;
import com.ilya.catalog.dto.catalog.SearchNamesDTO;
import com.ilya.catalog.dto.catalog.SmallContestCatalogDTO;
import com.ilya.catalog.dto.catalog.SmallSubmissionCatalogDTO;
import com.ilya.catalog.dto.catalog.SubGovernanceCatalogDTO;
import com.ilya.catalog.dto.catalog.SubmissionCatalogDTO;
import com.ilya.catalog.mapper.CatalogMapper;
import com.ilya.catalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogFacade {

    public static final CatalogMapper MAPPER = CatalogMapper.INSTANCE;
    private CatalogService catalogService;

    public static final Comparator<SmallSubmissionCatalogDTO> submissionComparator;
    public static final Comparator<SmallContestCatalogDTO> contestComparator;
    static {
        submissionComparator = Comparator.comparing(sub -> Integer.valueOf(sub.getPlace()));
        contestComparator = Comparator.comparing(SmallContestCatalogDTO::getId);
    }

    public List<SmallContestCatalogDTO> getContests(){
        List<SmallContestCatalogDTO> contests = catalogService.getContests();
        if (contests != null){
            return contests.stream().sorted(contestComparator).collect(Collectors.toList());
        }
        return contests;
    }

    public SubmissionCatalogDTO getSubmissionById(long id){
        return catalogService.getSubmissionById(id);
    }

    @Transactional
    public List<SubGovernanceCatalogDTO> getAllSubGovs(){
        return MAPPER.toSubGovernanceListDTO(catalogService.getSubGovernanceList());
    }

    public ContestCatalogDTO getContestById(long id){
        ContestCatalogDTO contest = catalogService.getContestById(id);
        if (contest != null && contest.getSubmissions() != null) {
            List<SmallSubmissionCatalogDTO> sortedSubmissions = contest.getSubmissions().stream().sorted(submissionComparator).collect(Collectors.toList());
            contest.setSubmissions(sortedSubmissions);
        }
        return contest;
    }

    public List<SmallContestCatalogDTO> getFilteredContests(FilterEntity filterEntity){
        List<SmallContestCatalogDTO> contests = catalogService.getFilteredContests(filterEntity);
        if (contests != null){
            if ("ASC".equals(filterEntity.getOrder())) {
                return contests.stream().sorted(contestComparator).collect(Collectors.toList());
            }
            return contests.stream().sorted(Collections.reverseOrder(contestComparator)).collect(Collectors.toList());
        }
        return contests;
    }

    public List<SmallContestCatalogDTO> getContestsByContainingName(String name){
        return catalogService.getContestByContainingName(name);
    }

    public List<SearchNamesDTO> getContestNames(String search){
        List<SearchNamesDTO> names = catalogService.getContestNames(search);
        List<SearchNamesDTO> result = new ArrayList<>(5);
        for (SearchNamesDTO name : names){
            String replacement = "<mark>" + search + "</mark>";
            name.setName(name.getName().replaceFirst("(?i)"+search, replacement));
            result.add(name);
            if (result.size() == 5){
                break;
            }
        }
        return result;
    }


    @Autowired
    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }
}
