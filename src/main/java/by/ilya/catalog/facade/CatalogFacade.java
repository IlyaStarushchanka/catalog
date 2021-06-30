package by.ilya.catalog.facade;

import by.ilya.catalog.dto.catalog.ContestCatalogDTO;
import by.ilya.catalog.dto.catalog.FilterEntity;
import by.ilya.catalog.dto.catalog.SmallContestCatalogDTO;
import by.ilya.catalog.dto.catalog.SmallSubmissionCatalogDTO;
import by.ilya.catalog.dto.catalog.SubGovernanceCatalogDTO;
import by.ilya.catalog.dto.catalog.SubmissionCatalogDTO;
import by.ilya.catalog.mapper.CatalogMapper;
import by.ilya.catalog.service.CatalogService;
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

    @Transactional
    public List<SmallContestCatalogDTO> getContests(){
        List<SmallContestCatalogDTO> contests = MAPPER.toSmallContestListDTO(catalogService.getContests());
        if (contests != null){
            return contests.stream().sorted(contestComparator).collect(Collectors.toList());
        }
        return contests;
    }

    @Transactional
    public SubmissionCatalogDTO getSubmissionById(long id){
        return MAPPER.toSubmissionDTO(catalogService.getSubmissionById(id));
    }

    @Transactional
    public List<SubGovernanceCatalogDTO> getAllSubGovs(){
        return MAPPER.toSubGovernanceListDTO(catalogService.getSubGovernanceList());
    }

    @Transactional
    public ContestCatalogDTO getContestById(long id){
        ContestCatalogDTO contest = MAPPER.toContestDTO(catalogService.getContestById(id));
        if (contest != null && contest.getSubmissions() != null) {
            List<SmallSubmissionCatalogDTO> sortedSubmissions = contest.getSubmissions().stream().sorted(submissionComparator).collect(Collectors.toList());
            contest.setSubmissions(sortedSubmissions);
        }
        return contest;
    }

    @Transactional
    public List<SmallContestCatalogDTO> getFilteredContests(FilterEntity filterEntity){
        List<SmallContestCatalogDTO> contests = MAPPER.toSmallContestListDTO(
                catalogService.getFilteredContests(filterEntity)
        );
        if (contests != null){
            if ("ASC".equals(filterEntity.getOrder())) {
                return contests.stream().sorted(contestComparator).collect(Collectors.toList());
            }
            Collections.reverse(contests.stream().sorted(contestComparator).collect(Collectors.toList()));
            return contests;
        }
        return contests;
    }

    @Transactional
    public List<SmallContestCatalogDTO> getContestsByContainingName(String name){
        return MAPPER.toSmallContestListDTO(catalogService.getContestByContainingName(name));
    }

    @Transactional
    public List<String> getContestNames(String search){
        List<String> names = catalogService.getContestNames(search);
        List<String> result = new ArrayList<>(3);
        for (String name : names){
            String replacement = "<mark>" + search + "</mark>";
            result.add(name.replaceFirst(search, replacement));
            if (result.size() == 3){
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
