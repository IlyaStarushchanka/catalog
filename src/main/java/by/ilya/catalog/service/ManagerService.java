package by.ilya.catalog.service;

import by.ilya.catalog.domain.Manager;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ManagerService extends CrudService<Manager> {

    Manager getManagerByUsername(String userName);

}
