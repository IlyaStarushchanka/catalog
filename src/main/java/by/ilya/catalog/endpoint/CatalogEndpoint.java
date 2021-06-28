package by.ilya.catalog.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogEndpoint {

    /*@Autowired
    private CityRepository cityRepository;*/

    @GetMapping("/catalog")
    public String findCities(Model model) {
        return "catalog/index";
    }

}
