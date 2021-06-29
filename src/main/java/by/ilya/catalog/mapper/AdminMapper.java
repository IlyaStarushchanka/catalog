package by.ilya.catalog.mapper;

import by.ilya.catalog.domain.Author;
import by.ilya.catalog.domain.Contest;
import by.ilya.catalog.domain.FileDB;
import by.ilya.catalog.domain.FreeTonAddress;
import by.ilya.catalog.domain.Manager;
import by.ilya.catalog.domain.SubGovernance;
import by.ilya.catalog.domain.Submission;
import by.ilya.catalog.dto.admin.AuthorDTO;
import by.ilya.catalog.dto.admin.ContestDTO;
import by.ilya.catalog.dto.admin.ManagerDTO;
import by.ilya.catalog.dto.admin.ResponseFile;
import by.ilya.catalog.dto.admin.SubGovernanceDTO;
import by.ilya.catalog.dto.admin.SubmissionDTO;
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
    List<ContestDTO> toContestListDTO(List<Contest> contests);

    @Mapping(target = "freetonAddresses", source = "freeTonAddresses", qualifiedByName = "mapFreeTonAddresses")
    Author toState (AuthorDTO author);
    @Mapping(target = "freeTonAddresses", source = "freetonAddresses", qualifiedByName = "mapFreeTonAddressesToDTO")
    AuthorDTO toDTO (Author author);

    List<AuthorDTO> toAuthorListDTO(List<Author> authors);

    Submission toState (SubmissionDTO submission);
    @Mapping(target = "author.submissions", ignore = true)
    @Mapping(target = "image", source = "image", qualifiedByName = "mapSubmissionImage")
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
            @Mapping(target = "contest.subGovernance.contests", ignore = true)
    })
    SubmissionDTO mapWithoutSubmissions(Submission submission);

    @Named("mapSubmissionImage")
    default String mapSubmissionImage() {
        if (addresses != null) {
            return Arrays.stream(addresses).map(FreeTonAddress::new).collect(Collectors.toList());
        }
        return null;
    }

    @Named("mapFreeTonAddresses")
    default List<FreeTonAddress> mapFreeTonAddresses(String[] addresses) {
        if (addresses != null) {
            return Arrays.stream(addresses).map(FreeTonAddress::new).collect(Collectors.toList());
        }
        return null;
    }

    @Named("mapFreeTonAddressesToDTO")
    default String[] mapFreeTonAddressesToDTO(List<FreeTonAddress> addresses) {
        if (addresses != null) {
            return addresses.stream().map(FreeTonAddress::getAddress).toArray(String[]::new);
        }
        return null;
    }

}
