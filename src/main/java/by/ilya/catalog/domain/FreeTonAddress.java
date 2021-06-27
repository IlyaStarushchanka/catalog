package by.ilya.catalog.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FreeTonAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String address;

    public FreeTonAddress() {
    }

    public FreeTonAddress(String address) {
        this.address = address;
    }

    public FreeTonAddress(String address, Author author) {
        this.address = address;
    }

    public FreeTonAddress(Long id, String address) {
        this.id = id;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
