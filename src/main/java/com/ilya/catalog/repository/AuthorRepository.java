package com.ilya.catalog.repository;

import com.ilya.catalog.domain.Author;
import com.ilya.catalog.dto.admin.SmallAuthorAdminDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select new com.ilya.catalog.dto.admin.SmallAuthorAdminDTO(a.id, a.freetonForumNickname) " +
            "from Author a")
    List<SmallAuthorAdminDTO> findAdminAuthors();

    @Query(value="select addr.address from free_ton_address as addr inner join author_freeton_addresses as a_addr " +
            "ON a_addr.freeton_addresses_id = addr.id where a_addr.author_id = ?1",
            nativeQuery = true)
    String[] findAddressesByAuthorId(long authorId);

}
