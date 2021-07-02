package com.ilya.catalog.service.admin;

import com.ilya.catalog.domain.Manager;
import com.ilya.catalog.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    private ManagerRepository managerRepository;

    @Override
    public List<Manager> getList() {
        return (List<Manager>)managerRepository.findAll();
    }

    @Override
    public Manager getById(long id) {
        return managerRepository.findById(id).orElse(null);
    }

    @Override
    public Manager getManagerByUsername(String userName) {
        return managerRepository.findByNickName(userName);
    }

    @Override
    public Manager create(Manager manager) {
        manager.setPassword(BCrypt.hashpw(manager.getPassword(), BCrypt.gensalt("$2a", 10)));
        return managerRepository.saveAndFlush(manager);
    }

    @Override
    public void delete(long id) {
        managerRepository.deleteById(id);
    }

    @Autowired
    public void setManagerRepository(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }
}
