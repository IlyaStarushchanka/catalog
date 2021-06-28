package by.ilya.catalog.service.admin;

import by.ilya.catalog.domain.Manager;
import org.springframework.stereotype.Service;


@Service
public interface ManagerService extends CrudService<Manager> {

    Manager getManagerByUsername(String userName);

}
