package by.ilya.catalog.service;

import by.ilya.catalog.domain.Author;
import by.ilya.catalog.domain.Contest;
import by.ilya.catalog.domain.SubGovernance;
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
        return submissionRepository.findAll();
    }

    @Override
    public Submission getById(long id) {
        return submissionRepository.getById(id);
    }

    @Override
    public void delete(long id) {
        submissionRepository.deleteById(id);
    }

    public void edit(Contest newContest, Author newAuthor, Submission submission, Submission newSubmissionData) {
        if (!submission.getContest().equals(newContest)){
            submission.getContest().getSubmissions().remove(submission);
            submission.setContest(newContest);
        }
        if (!submission.getAuthor().equals(newAuthor)){
            submission.getAuthor().getSubmissions().remove(submission);
            submission.setAuthor(newAuthor);
        }
        submission.setDescription(newSubmissionData.getDescription());
        submission.setNumber(newSubmissionData.getNumber());
        submission.setPlace(newSubmissionData.getPlace());
        submission.setPrize(newSubmissionData.getPrize());
        submission.setRate(newSubmissionData.getRate());
        submission.setAuthorFreeTonAddress(newSubmissionData.getAuthorFreeTonAddress());
        if (newContest != null && !newContest.getSubmissions().contains(submission)) {
            newContest.getSubmissions().add(submission);
        }
        if (newAuthor != null && !newAuthor.getSubmissions().contains(submission)) {
            newAuthor.getSubmissions().add(submission);
        }
        submissionRepository.saveAndFlush(submission);
    }
    public void edit(long id) {
        submissionRepository.deleteById(id);
    }
}
