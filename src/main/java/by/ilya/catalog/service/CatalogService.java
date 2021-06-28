package by.ilya.catalog.service;

import by.ilya.catalog.domain.Contest;
import by.ilya.catalog.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    @Autowired
    private ContestRepository contestRepository;

    public List<Contest> getContests(){
        return contestRepository.findAll();
    }

}
