package com.ilya.catalog.service.admin;

import com.ilya.catalog.domain.Author;
import com.ilya.catalog.dto.admin.SmallAuthorAdminDTO;
import com.ilya.catalog.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements CrudService<Author> {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getList() {
        return authorRepository.findAll();
    }


    public List<SmallAuthorAdminDTO> findAdminAuthors() {
        List<SmallAuthorAdminDTO> authors = authorRepository.findAdminAuthors();
        authors.forEach(e -> e.setFreeTonAddresses(authorRepository.findAddressesByAuthorId(e.getId())));
        return authors;
    }

    @Override
    public Author getById(long id) {
        return authorRepository.getById(id);
    }

    public Author edit(Author author) {
        return authorRepository.saveAndFlush(author);
    }

    @Override
    public void delete(long id) {

    }
}
