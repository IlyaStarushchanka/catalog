package com.ilya.catalog.service.admin;

import com.ilya.catalog.domain.Author;
import com.ilya.catalog.domain.Contest;
import com.ilya.catalog.domain.LinkDB;
import com.ilya.catalog.domain.Submission;
import com.ilya.catalog.dto.admin.LinkDBDTO;
import com.ilya.catalog.repository.ContestRepository;
import com.ilya.catalog.repository.LinkDBRepository;
import com.ilya.catalog.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubmissionServiceImpl implements CrudService<Submission> {

    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private LinkDBRepository linkDBRepository;
    @Autowired
    private ContestRepository contestRepository;

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
        contestRepository.saveAndFlush(submission.getContest());
        submission.setSmallDescription(newSubmissionData.getSmallDescription());
        submission.setBigDescription(newSubmissionData.getBigDescription());
        submission.setNumber(newSubmissionData.getNumber());
        submission.setPlace(newSubmissionData.getPlace());
        submission.setPrize(newSubmissionData.getPrize());
        submission.setRate(newSubmissionData.getRate());
        submission.setAuthorFreeTonAddress(newSubmissionData.getAuthorFreeTonAddress());
        submission.setStatisticsLink(newSubmissionData.getStatisticsLink());
        submission.setContest(newContest);
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

    public byte[] getSubmissionImage(long id) {
        Submission submission = submissionRepository.findById(id).orElse(null);
        if (submission != null){
            return submission.getImage();
        }
        return null;
    }

    public void deleteSubmissionLink(long submissionId, long linkId) {
        Submission submission = submissionRepository.findById(submissionId).orElse(null);
        LinkDB link = linkDBRepository.findById(linkId).orElse(null);
        if (submission != null){
            submission.getLinks().remove(link);
        }
        if (link != null){
            linkDBRepository.deleteById(link.getId());
        }
    }

    public void addSubmissionLink(LinkDBDTO linkDBDTO) {
        Submission submission = submissionRepository.findById(linkDBDTO.getSubmissionId()).orElse(null);
        if (submission != null){
            LinkDB linkDB = new LinkDB();
            linkDB.setName(linkDBDTO.getName());
            linkDB.setUrl(linkDBDTO.getUrl());
            linkDB.setSubmission(submission);
            linkDBRepository.save(linkDB);
            submission.getLinks().add(linkDB);
        }
    }
}
