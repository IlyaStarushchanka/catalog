package by.ilya.catalog.service;

import by.ilya.catalog.domain.Author;
import by.ilya.catalog.repository.AuthorRepository;
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
        return null;
    }

    @Override
    public Author getById(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
