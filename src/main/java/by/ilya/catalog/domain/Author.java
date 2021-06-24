package by.ilya.catalog.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String telegramNickname;
    private String freetonForumNickname;
    @OneToMany(cascade = CascadeType.ALL)
    private List<FreeTonAddress> freetonAddresses = new ArrayList<>();
    @OneToMany
    private List<Submission> submissions;

    public boolean hasFreeTonAddressByName(String address){
        if (address == null || address.isEmpty()){
            return true;
        }
        for (FreeTonAddress freeTonAddress : freetonAddresses) {
            if(freeTonAddress.getAddress().equals(address)){
                return true;
            }
        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelegramNickname() {
        return telegramNickname;
    }

    public void setTelegramNickname(String telegramNickname) {
        this.telegramNickname = telegramNickname;
    }

    public String getFreetonForumNickname() {
        return freetonForumNickname;
    }

    public void setFreetonForumNickname(String freetonForumNickname) {
        this.freetonForumNickname = freetonForumNickname;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    public List<FreeTonAddress> getFreetonAddresses() {
        return freetonAddresses;
    }

    public void setFreetonAddresses(List<FreeTonAddress> freetonAddresses) {
        this.freetonAddresses = freetonAddresses;
    }
}
