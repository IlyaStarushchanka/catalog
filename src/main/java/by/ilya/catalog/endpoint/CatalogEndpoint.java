package by.ilya.catalog.endpoint;

import by.ilya.catalog.dto.catalog.FilterEntity;
import by.ilya.catalog.facade.CatalogFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CatalogEndpoint {


    private CatalogFacade catalogFacade;

    @GetMapping("/")
    public String contests(Model model) {
        model.addAttribute("contests", catalogFacade.getContests());
        model.addAttribute("subGovernances", catalogFacade.getAllSubGovs());
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
    public String getSubmission(@ModelAttribute FilterEntity filterEntity, Model model) {
        model.addAttribute("filterEntity", filterEntity);
        model.addAttribute("contests", catalogFacade.getFilteredContests(filterEntity.getSubGovesIds(), filterEntity.getPrizeFundFrom(), filterEntity.getPrizeFundTo()));
        model.addAttribute("subGovernances", catalogFacade.getAllSubGovs());
        return "catalog/index";
    }

    @Autowired
    public void setCatalogFacade(CatalogFacade catalogFacade) {
        this.catalogFacade = catalogFacade;
    }
}
