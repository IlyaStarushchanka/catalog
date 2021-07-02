package com.ilya.catalog.repository;

import com.ilya.catalog.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Manager findByNickName(String nickName);

}
