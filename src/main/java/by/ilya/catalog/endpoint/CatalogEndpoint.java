package by.ilya.catalog.endpoint;

import by.ilya.catalog.dto.catalog.FilterEntity;
import by.ilya.catalog.dto.catalog.SmallContestCatalogDTO;
import by.ilya.catalog.facade.CatalogFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CatalogEndpoint {


    private CatalogFacade catalogFacade;

    @GetMapping("/")
    public String contests(Model model) {
        model.addAttribute("contests", catalogFacade.getContests());
        model.addAttribute("subGovernances", catalogFacade.getAllSubGovs());
        return "catalog/index";
    }

    @GetMapping("/catalog")
    public String catalog(@RequestParam(value = "subGovId", required=false) Long subGovId, Model model) {
        FilterEntity filterEntity = new FilterEntity();

        filterEntity.setSubGovesIds(new ArrayList<>());
        filterEntity.getSubGovesIds().add(subGovId);
        model.addAttribute("subGovernances", catalogFacade.getAllSubGovs());
        //model.addAttribute("contestNames", catalogFacade.getContestNames());
        if (subGovId != null) {
            model.addAttribute("contests", catalogFacade.getFilteredContests(filterEntity));
            model.addAttribute("subGovernances", catalogFacade.getAllSubGovs());
            model.addAttribute("subGovId", subGovId);
        } else {
            model.addAttribute("contests", catalogFacade.getContests());
        }

        return "catalog/index";
    }

    @GetMapping("/contest")
    public String getContest(@RequestParam(value = "id") long id, Model model) {
        model.addAttribute("contest", catalogFacade.getContestById(id));
        return "catalog/contest";
    }

    @GetMapping("/contest/search")
    public String searchByName(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("contests", catalogFacade.getContestsByContainingName(name));
        model.addAttribute("subGovernances", catalogFacade.getAllSubGovs());
        return "catalog/index";
    }


    @GetMapping("/submission")
    public String getSubmission(@RequestParam(value = "id") long id, Model model) {
        model.addAttribute("submission", catalogFacade.getSubmissionById(id));
        return "catalog/submission";
    }

    @GetMapping("/contest/filter")
    @ResponseBody
    public List<SmallContestCatalogDTO> getFilteredContests(@ModelAttribute FilterEntity filterEntity, Model model){
        return catalogFacade.getFilteredContests(filterEntity);
    }

    @GetMapping("/contest/names")
    @ResponseBody
    public List<String> getContestNames(@RequestParam("search") String search) {
        return catalogFacade.getContestNames(search);
    }

    @Autowired
    public void setCatalogFacade(CatalogFacade catalogFacade) {
        this.catalogFacade = catalogFacade;
    }
}
