package by.ilya.catalog.facade.admin;

import by.ilya.catalog.domain.Author;
import by.ilya.catalog.domain.FreeTonAddress;
import by.ilya.catalog.dto.admin.AuthorDTO;
import by.ilya.catalog.mapper.AdminMapper;
import by.ilya.catalog.service.admin.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminAuthorFacade {

    private AuthorServiceImpl authorServiceImpl;
    private static final AdminMapper MAPPER = AdminMapper.INSTANCE;

    public List<AuthorDTO> getList() {
        return MAPPER.toAuthorListDTO(authorServiceImpl.getList());
    }

    @Transactional
    public AuthorDTO create(AuthorDTO authorDTO) {
        Author author = MAPPER.toState(authorDTO);
        authorServiceImpl.create(author);
        return MAPPER.toDTO(author);
    }

    @Transactional
    public AuthorDTO getById(long id) {
        return MAPPER.toDTO(authorServiceImpl.getById(id));
    }

    @Transactional
    public List<AuthorDTO> edit(AuthorDTO authorDTO) {
        List<FreeTonAddress> newAddresses = new ArrayList<>();
        Author author = authorServiceImpl.getById(authorDTO.getId());
        author.setFreetonForumNickname(authorDTO.getFreetonForumNickname());
        for(String address : authorDTO.getFreeTonAddresses()) {
            if (!author.hasFreeTonAddressByName(address)) {
                FreeTonAddress newAddress = new FreeTonAddress(address, author);
                newAddresses.add(newAddress);
            }
        }
        if (!newAddresses.isEmpty()){
            author.getFreetonAddresses().addAll(newAddresses);
        }
        authorServiceImpl.edit(author);
        return MAPPER.toAuthorListDTO(authorServiceImpl.getList());
    }

    @Autowired
    public void setAuthorServiceImpl(AuthorServiceImpl authorServiceImpl) {
        this.authorServiceImpl = authorServiceImpl;
    }
}
