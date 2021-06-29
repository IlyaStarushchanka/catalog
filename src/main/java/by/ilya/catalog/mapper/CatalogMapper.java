package by.ilya.catalog.mapper;

import by.ilya.catalog.domain.Contest;
import by.ilya.catalog.domain.FileDB;
import by.ilya.catalog.domain.LinkDB;
import by.ilya.catalog.domain.SubGovernance;
import by.ilya.catalog.domain.Submission;
import by.ilya.catalog.dto.admin.ContestDTO;
import by.ilya.catalog.dto.admin.LinkDBDTO;
import by.ilya.catalog.dto.admin.ResponseFile;
import by.ilya.catalog.dto.admin.SubmissionDTO;
import by.ilya.catalog.dto.catalog.ContestCatalogDTO;
import by.ilya.catalog.dto.catalog.SmallContestCatalogDTO;
import by.ilya.catalog.dto.catalog.SmallSubmissionCatalogDTO;
import by.ilya.catalog.dto.catalog.SubGovernanceCatalogDTO;
import by.ilya.catalog.dto.catalog.SubmissionCatalogDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Mapper
public interface CatalogMapper {

    CatalogMapper INSTANCE = Mappers.getMapper( CatalogMapper.class );

    List<SmallContestCatalogDTO> toSmallContestListDTO(List<Contest> contests);

    @Mapping(target = "winnersCount", expression="java(contest.getSubmissions() != null ? contest.getSubmissions().size() : 0)")
    SmallContestCatalogDTO toSmallContestDTO(Contest contest);

    @IterableMapping(qualifiedByName="mapSmallSubmissions")
    ContestCatalogDTO toContestDTO(Contest contest);

    List<SubGovernanceCatalogDTO> toSubGovernanceListDTO (List<SubGovernance> subGovernances);

    @Mapping(target = "image", source = "submission", qualifiedByName = "mapSubmissionImage")
    @Mapping(target = "statisticsShortLink", source = "statisticsLink", qualifiedByName = "subStringUrl")
    SubmissionCatalogDTO toSubmissionDTO(Submission submission);

    @Mapping(target = "shortUrl", source = "url", qualifiedByName = "subStringUrl")
    LinkDBDTO toDTO(LinkDB link);

    @Named("mapSmallSubmissions")
    List<SmallSubmissionCatalogDTO> toSmallSubmissionListDTO(List<Submission> submissions);

    @Mapping(target = "filesCount", expression="java(submission.getFiles() != null ? submission.getFiles().size() : 0)")
    @Mapping(target = "linksCount", expression="java(submission.getLinks() != null ? submission.getLinks().size() : 0)")
    @Mapping(target = "authorFreeTonAddress", source = "authorFreeTonAddress", qualifiedByName = "mapSmallFreeTonAddress")
    @Mapping(target = "image", source = "submission", qualifiedByName = "mapSubmissionImage")
    SmallSubmissionCatalogDTO toSmallSubmissionDTO(Submission submission);

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
    @Named("mapSmallFreeTonAddress")
    default String mapSmallFreeTonAddress (String authorFreeTonAddress){
        return authorFreeTonAddress.substring(0, 6) +
                "..." +
                authorFreeTonAddress.substring(authorFreeTonAddress.length() - 5, authorFreeTonAddress.length() - 1);
    }

    @Named("subStringUrl")
    default String subStringUrl (String url){
        if (url != null && url.length() > 40){
            return url.substring(0,40) + "...";
        }
        return url;
    }

    @Named("mapSubmissionImage")
    default String mapSubmissionImage(Submission submission) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/submission/img/")
                .path(String.valueOf(submission.getId()))
                .toUriString();
    }

}
