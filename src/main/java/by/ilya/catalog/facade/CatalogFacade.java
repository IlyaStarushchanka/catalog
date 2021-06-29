package by.ilya.catalog.facade;

import by.ilya.catalog.dto.catalog.ContestCatalogDTO;
import by.ilya.catalog.dto.catalog.SmallContestCatalogDTO;
import by.ilya.catalog.dto.catalog.SubGovernanceCatalogDTO;
import by.ilya.catalog.dto.catalog.SubmissionCatalogDTO;
import by.ilya.catalog.mapper.CatalogMapper;
import by.ilya.catalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogFacade {

    public static final CatalogMapper MAPPER = CatalogMapper.INSTANCE;
    private CatalogService catalogService;

    public List<SmallContestCatalogDTO> getContests(){
        return MAPPER.toSmallContestListDTO(catalogService.getContests());
    }

    public SubmissionCatalogDTO getSubmissionById(long id){
        return MAPPER.toSubmissionDTO(catalogService.getSubmissionById(id));
    }

    public List<SubGovernanceCatalogDTO> getAllSubGovs(){
        return MAPPER.toSubGovernanceListDTO(catalogService.getSubGovernanceList());
    }

    public ContestCatalogDTO getContestById(long id){
        return MAPPER.toContestDTO(catalogService.getContestById(id));
    }

    @Autowired
    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }
}
