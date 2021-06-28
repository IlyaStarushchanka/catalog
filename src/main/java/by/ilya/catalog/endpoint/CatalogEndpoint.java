package by.ilya.catalog.endpoint;

import by.ilya.catalog.facade.CatalogFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CatalogEndpoint {


    private CatalogFacade catalogFacade;

    @GetMapping("/")
    public String contests(Model model) {
        model.addAttribute("contests", catalogFacade.getContests());
        return "catalog/index";
    }

    @Autowired
    public void setCatalogFacade(CatalogFacade catalogFacade) {
        this.catalogFacade = catalogFacade;
    }
}
