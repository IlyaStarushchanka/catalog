package com.ilya.catalog.service.admin;

import com.ilya.catalog.domain.Manager;
import org.springframework.stereotype.Service;


@Service
public interface ManagerService extends CrudService<Manager> {

    Manager getManagerByUsername(String userName);

}
