package by.ilya.catalog.service;

import by.ilya.catalog.domain.Submission;
import by.ilya.catalog.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubmissionServiceImpl implements CrudService<Submission> {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Transactional
    public Submission create(Submission submission) {
        return submissionRepository.save(submission);
    }

    @Override
    public List<Submission> getList() {
        return null;
    }

    @Override
    public Submission getById(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
