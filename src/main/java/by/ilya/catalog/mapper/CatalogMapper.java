package by.ilya.catalog.mapper;

import by.ilya.catalog.domain.Author;
import by.ilya.catalog.domain.Contest;
import by.ilya.catalog.domain.FreeTonAddress;
import by.ilya.catalog.domain.Manager;
import by.ilya.catalog.domain.SubGovernance;
import by.ilya.catalog.domain.Submission;
import by.ilya.catalog.dto.AuthorDTO;
import by.ilya.catalog.dto.ContestDTO;
import by.ilya.catalog.dto.ManagerDTO;
import by.ilya.catalog.dto.SubGovernanceDTO;
import by.ilya.catalog.dto.SubmissionDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CatalogMapper {

    CatalogMapper INSTANCE = Mappers.getMapper( CatalogMapper.class );

    List<ManagerDTO> toManagerListDTO(List<Manager> managers);
    ManagerDTO toDTO(Manager manager);


    SubGovernanceDTO toDTO(SubGovernance subGovernance);

    List<SubGovernanceDTO> toSubGovernancesListDTO(List<SubGovernance> subGovernances);
    SubGovernance toState (SubGovernanceDTO subGovernance);

    Contest toState (ContestDTO contest);
    @Mapping(target = "contest.subGovernance.contests", ignore = true)
    ContestDTO toDTO (Contest contest);

    @IterableMapping(qualifiedByName="mapWithoutContests")
    List<ContestDTO> toContestListDTO(List<Contest> contests);

    @Mapping(target = "freetonAddresses", source = "freeTonAddresses", qualifiedByName = "mapFreeTonAddresses")
    Author toState (AuthorDTO author);
    @Mapping(target = "freeTonAddresses", source = "freetonAddresses", qualifiedByName = "mapFreeTonAddressesToDTO")
    AuthorDTO toDTO (Author author);

    List<AuthorDTO> toAuthorListDTO(List<Author> authors);

    Submission toState (SubmissionDTO submission);
    SubmissionDTO toDTO (Submission submission);

    List<SubmissionDTO> toSubmissionListDTO(List<Submission> submissions);

    default long map(Timestamp value){
        return value.getTime();
    }

    default Timestamp map(long value){
        return new Timestamp(value);
    }

    @Named("mapWithoutContests")
    @Mapping(target = "subGovernance.contests", ignore = true)
    ContestDTO mapWithoutContests(Contest contest);

    @Named("mapFreeTonAddresses")
    default List<FreeTonAddress> mapFreeTonAddresses(String[] addresses) {
        return Arrays.stream(addresses).map(FreeTonAddress::new).collect(Collectors.toList());
    }

    @Named("mapFreeTonAddressesToDTO")
    default String[] mapFreeTonAddressesToDTO(List<FreeTonAddress> addresses) {
        return addresses.stream().map(FreeTonAddress::getAddress).toArray(String[]::new);
    }

}
