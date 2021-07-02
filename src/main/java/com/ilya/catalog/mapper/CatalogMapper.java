package com.ilya.catalog.mapper;

import com.ilya.catalog.domain.Contest;
import com.ilya.catalog.domain.FileDB;
import com.ilya.catalog.domain.LinkDB;
import com.ilya.catalog.domain.SubGovernance;
import com.ilya.catalog.domain.Submission;
import com.ilya.catalog.dto.admin.LinkDBDTO;
import com.ilya.catalog.dto.admin.ResponseFile;
import com.ilya.catalog.dto.catalog.ContestCatalogDTO;
import com.ilya.catalog.dto.catalog.SmallContestCatalogDTO;
import com.ilya.catalog.dto.catalog.SmallSubmissionCatalogDTO;
import com.ilya.catalog.dto.catalog.SubGovernanceCatalogDTO;
import com.ilya.catalog.dto.catalog.SubmissionCatalogDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Mapper
public interface CatalogMapper {

    CatalogMapper INSTANCE = Mappers.getMapper( CatalogMapper.class );

    List<SubGovernanceCatalogDTO> toSubGovernanceListDTO (List<SubGovernance> subGovernances);

    @Mapping(target = "shortUrl", source = "url", qualifiedByName = "subStringUrl")
    LinkDBDTO toDTO(LinkDB link);

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
