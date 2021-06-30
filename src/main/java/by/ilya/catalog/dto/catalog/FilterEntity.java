package by.ilya.catalog.dto.catalog;

import java.util.List;

public class FilterEntity {

    private Integer prizeFundFrom;
    private Integer prizeFundTo;
    private List<Long> subGovesIds;

    public Integer getPrizeFundFrom() {
        return prizeFundFrom;
    }

    public void setPrizeFundFrom(Integer prizeFundFrom) {
        this.prizeFundFrom = prizeFundFrom;
    }

    public Integer getPrizeFundTo() {
        return prizeFundTo;
    }

    public void setPrizeFundTo(Integer prizeFundTo) {
        this.prizeFundTo = prizeFundTo;
    }

    public List<Long> getSubGovesIds() {
        return subGovesIds;
    }

    public void setSubGovesIds(List<Long> subGovesIds) {
        this.subGovesIds = subGovesIds;
    }
}
