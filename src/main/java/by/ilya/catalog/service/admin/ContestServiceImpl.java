package by.ilya.catalog.service.admin;

import by.ilya.catalog.domain.Contest;
import by.ilya.catalog.domain.SubGovernance;
import by.ilya.catalog.repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContestServiceImpl implements CrudService<Contest> {

    private ContestRepository contestRepository;

    @Transactional
    @Override
    public Contest create(Contest contest) {
        return contestRepository.save(contest);
    }
    @Override
    public List<Contest> getList() {
        return contestRepository.findAll();
    }

    @Override
    public Contest getById(long id) {
        return contestRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(long id) {
        contestRepository.deleteById(id);
    }

    public void edit(SubGovernance newSubGovernance, Contest contest, Contest newContestData) {
        if (!contest.getSubGovernance().equals(newSubGovernance)){
            contest.getSubGovernance().getContests().remove(contest);
        }
        contest.setSmallDescription(newContestData.getSmallDescription());
        contest.setBigDescription(newContestData.getBigDescription());
        contest.setName(newContestData.getName());
        contest.setStatus(newContestData.getStatus());
        contest.setSubmissionFrom(newContestData.getSubmissionFrom());
        contest.setSubmissionTo(newContestData.getSubmissionTo());
        contest.setVotingFrom(newContestData.getVotingFrom());
        contest.setVotingTo(newContestData.getVotingTo());
        contest.setSubGovernance(newSubGovernance);
        contest.setPrizeFund(newContestData.getPrizeFund());
        if (newSubGovernance != null && !newSubGovernance.getContests().contains(contest)) {
            newSubGovernance.getContests().add(contest);
        }
        contestRepository.saveAndFlush(contest);
    }

    public void update(Contest contest){
        contestRepository.saveAndFlush(contest);
    }

    @Autowired
    public void setContestRepository(ContestRepository contestRepository) {
        this.contestRepository = contestRepository;
    }
}
