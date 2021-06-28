package by.ilya.catalog.mapper;

import by.ilya.catalog.domain.Contest;
import by.ilya.catalog.dto.catalog.SmallContestCatalogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CatalogMapper {

    CatalogMapper INSTANCE = Mappers.getMapper( CatalogMapper.class );

    List<SmallContestCatalogDTO> toSmallContestListDTO(List<Contest> contests);

    @Mapping(target = "winnersCount", expression="java(contest.getSubmissions() != null ? contest.getSubmissions().size() : 0)")
    SmallContestCatalogDTO toDTO(Contest contest);

}
