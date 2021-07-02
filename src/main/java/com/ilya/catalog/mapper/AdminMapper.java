package com.ilya.catalog.mapper;

import com.ilya.catalog.domain.Author;
import com.ilya.catalog.domain.Contest;
import com.ilya.catalog.domain.FileDB;
import com.ilya.catalog.domain.FreeTonAddress;
import com.ilya.catalog.domain.Manager;
import com.ilya.catalog.domain.SubGovernance;
import com.ilya.catalog.domain.Submission;
import com.ilya.catalog.dto.admin.AuthorDTO;
import com.ilya.catalog.dto.admin.ContestDTO;
import com.ilya.catalog.dto.admin.ManagerDTO;
import com.ilya.catalog.dto.admin.ResponseFile;
import com.ilya.catalog.dto.admin.SubGovernanceDTO;
import com.ilya.catalog.dto.admin.SubmissionDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface AdminMapper {

    AdminMapper INSTANCE = Mappers.getMapper( AdminMapper.class );

    List<ManagerDTO> toManagerListDTO(List<Manager> managers);
    ManagerDTO toDTO(Manager manager);


    SubGovernanceDTO toDTO(SubGovernance subGovernance);

    List<SubGovernanceDTO> toSubGovernancesListDTO(List<SubGovernance> subGovernances);
    SubGovernance toState (SubGovernanceDTO subGovernance);

    Contest toState (ContestDTO contest);
    @Mapping(target = "subGovernance.contests", ignore = true)
    ContestDTO toDTO (Contest contest);

    @IterableMapping(qualifiedByName="mapWithoutContests")
    List<ContestDTO> toContestListFromSetDTO(Set<Contest> contests);

    @IterableMapping(qualifiedByName="mapWithoutContests")
    List<ContestDTO> toContestListDTO(List<Contest> contests);

    @Mapping(target = "freetonAddresses", source = "freeTonAddresses", qualifiedByName = "mapFreeTonAddresses")
    Author toState (AuthorDTO author);
    @Mapping(target = "freeTonAddresses", source = "freetonAddresses", qualifiedByName = "mapFreeTonAddressesToDTO")
    AuthorDTO toDTO (Author author);

    List<AuthorDTO> toAuthorListDTO(List<Author> authors);

    @Mapping(target = "image", ignore = true)
    Submission toState (SubmissionDTO submission);
    @Mapping(target = "author.submissions", ignore = true)
    @Mapping(target = "image", source = "submission", qualifiedByName = "mapSubmissionImage")
    SubmissionDTO toDTO (Submission submission);

    default ResponseFile toDTO (FileDB file){
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/")
                .path(file.getId())
                .toUriString();
        return new ResponseFile(
                file.getName(),
                fileDownloadUri,
                file.getType(),
                file.getData().length,
                file.getId());
    }

    @IterableMapping(qualifiedByName="mapWithoutSubmissions")
    List<SubmissionDTO> toSubmissionListDTO(Set<Submission> submissions);

    default long map(Timestamp value){
        return value.getTime();
    }

    default Timestamp map(long value){
        return new Timestamp(value);
    }

    @Named("mapWithoutContests")
    @Mapping(target = "subGovernance.contests", ignore = true)
    ContestDTO mapWithoutContests(Contest contest);

    @Named("mapWithoutSubmissions")
    @Mappings({
            @Mapping(target = "author.submissions", ignore = true),
            @Mapping(target = "contest.submissions", ignore = true),
            @Mapping(target = "contest.subGovernance.contests", ignore = true),
            @Mapping(target = "image", source = "submission", qualifiedByName = "mapSubmissionImage")
    })
    SubmissionDTO mapWithoutSubmissions(Submission submission);

    @Named("mapSubmissionImage")
    default String mapSubmissionImage(Submission submission) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/submission/img/")
                .path(String.valueOf(submission.getId()))
                .toUriString();
    }

    @Named("mapFreeTonAddresses")
    default Set<FreeTonAddress> mapFreeTonAddresses(String[] addresses) {
        if (addresses != null) {
            return Arrays.stream(addresses).map(FreeTonAddress::new).collect(Collectors.toSet());
        }
        return null;
    }

    @Named("mapFreeTonAddressesToDTO")
    default String[] mapFreeTonAddressesToDTO(Set<FreeTonAddress> addresses) {
        if (addresses != null) {
            return addresses.stream().map(FreeTonAddress::getAddress).toArray(String[]::new);
        }
        return null;
    }

}
