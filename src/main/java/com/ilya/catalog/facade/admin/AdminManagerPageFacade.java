package com.ilya.catalog.facade.admin;

import com.ilya.catalog.domain.AccessEnum;
import com.ilya.catalog.domain.Manager;
import com.ilya.catalog.dto.admin.ManagerDTO;
import com.ilya.catalog.mapper.AdminMapper;
import com.ilya.catalog.service.admin.ManagerService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminManagerPageFacade {

    private static final AdminMapper MAPPER = AdminMapper.INSTANCE;

    @Autowired
    private ManagerService managerService;

    public List<ManagerDTO> getList() {
        return MAPPER.toManagerListDTO(managerService.getList());
    }

    public Boolean isUserNameExist(String userName) {
        Manager manager = managerService.getManagerByUsername(userName);
        return manager != null;
    }

    public ManagerDTO getById(long id) {
        return MAPPER.toDTO(managerService.getById(id));
    }

    @Transactional
    public List<ManagerDTO> edit(ManagerDTO manager) throws NotFoundException {
        Manager existedManager = managerService.getById(manager.getId());
        if (existedManager == null){
            throw new NotFoundException("");
        }
        existedManager.setNickName(manager.getNickName());
        existedManager.setAccess(AccessEnum.valueOf(manager.getAccess()));
        return MAPPER.toManagerListDTO(managerService.getList());
    }

    public List<ManagerDTO> create(Manager manager) {
        managerService.create(manager);
        return MAPPER.toManagerListDTO(managerService.getList());
    }

    @Transactional
    public List<ManagerDTO> delete(long id, String userName) {
        Manager manager = managerService.getById(id);
        if (manager != null && !manager.getNickName().equals(userName)) {
            managerService.delete(id);
        }
        return getList();
    }
}
