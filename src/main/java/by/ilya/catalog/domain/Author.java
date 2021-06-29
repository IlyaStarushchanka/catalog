package by.ilya.catalog.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String freetonForumNickname;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<FreeTonAddress> freetonAddresses;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Submission> submissions = new HashSet<>();

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

    public String getFreetonForumNickname() {
        return freetonForumNickname;
    }

    public void setFreetonForumNickname(String freetonForumNickname) {
        this.freetonForumNickname = freetonForumNickname;
    }

    public Set<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Set<Submission> submissions) {
        this.submissions = submissions;
    }

    public Set<FreeTonAddress> getFreetonAddresses() {
        return freetonAddresses;
    }

    public void setFreetonAddresses(Set<FreeTonAddress> freetonAddresses) {
        this.freetonAddresses = freetonAddresses;
    }
}
