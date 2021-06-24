package by.ilya.catalog.facade;

import by.ilya.catalog.domain.Author;
import by.ilya.catalog.domain.FreeTonAddress;
import by.ilya.catalog.domain.SubGovernance;
import by.ilya.catalog.dto.AuthorDTO;
import by.ilya.catalog.dto.SubmissionDTO;
import by.ilya.catalog.mapper.CatalogMapper;
import by.ilya.catalog.service.AuthorServiceImpl;
import by.ilya.catalog.service.ContestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminAuthorFacade {

    private AuthorServiceImpl authorServiceImpl;
    private static final CatalogMapper MAPPER = CatalogMapper.INSTANCE;

    public List<AuthorDTO> getList() {
        return MAPPER.toAuthorListDTO(authorServiceImpl.getList());
    }

    @Transactional
    public List<AuthorDTO> create(AuthorDTO authorDTO) {
        Author author = MAPPER.toState(authorDTO);
        authorServiceImpl.create(author);
        for (FreeTonAddress address : author.getFreetonAddresses()){
            address.setAuthor(author);
        }
        return getList();
    }

    public AuthorDTO getById(long id) {
        return MAPPER.toDTO(authorServiceImpl.getById(id));
    }

    @Transactional
    public List<AuthorDTO> edit(AuthorDTO authorDTO) {
        List<FreeTonAddress> newAddresses = new ArrayList<>();
        Author author = authorServiceImpl.getById(authorDTO.getId());
        author.setFreetonForumNickname(authorDTO.getFreetonForumNickname());
        author.setTelegramNickname(authorDTO.getTelegramNickname());
        for(String address : authorDTO.getFreeTonAddresses()) {
            if (!author.hasFreeTonAddressByName(address)) {
                FreeTonAddress newAddress = new FreeTonAddress(address, author);
                newAddresses.add(newAddress);
            }
        }
        if (!newAddresses.isEmpty()){
            author.getFreetonAddresses().addAll(newAddresses);
        }
        return MAPPER.toAuthorListDTO(authorServiceImpl.getList());
    }

    @Autowired
    public void setAuthorServiceImpl(AuthorServiceImpl authorServiceImpl) {
        this.authorServiceImpl = authorServiceImpl;
    }
}
