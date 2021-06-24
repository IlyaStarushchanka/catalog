package by.ilya.catalog.facade;

import by.ilya.catalog.domain.AccessEnum;
import by.ilya.catalog.domain.Manager;
import by.ilya.catalog.dto.ManagerDTO;
import by.ilya.catalog.mapper.CatalogMapper;
import by.ilya.catalog.service.ManagerService;
import by.ilya.catalog.service.ManagerServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminManagerPageFacade {

    private static final CatalogMapper MAPPER = CatalogMapper.INSTANCE;

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
