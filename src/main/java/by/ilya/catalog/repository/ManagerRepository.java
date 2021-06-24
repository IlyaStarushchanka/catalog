package by.ilya.catalog.repository;

import by.ilya.catalog.domain.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, Long>{

    Manager findByNickName(String nickName);

}
