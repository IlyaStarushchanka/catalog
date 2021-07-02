package com.ilya.catalog.security;

import com.ilya.catalog.domain.Manager;
import com.ilya.catalog.service.admin.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userDetailsService")
public class ManagerDatabaseDetailsService implements UserDetailsService {

    @Autowired
    private ManagerService managerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager manager = managerService.getManagerByUsername(username);
        if (manager == null){
            throw new UsernameNotFoundException("Incorrect userName");
        }
        List<GrantedAuthority> auth = AuthorityUtils
                .commaSeparatedStringToAuthorityList("USER");
        if ("ADMIN".equals(manager.getAccess().name())) {
            auth = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ADMIN");
        }
        String password = manager.getPassword();
        return new org.springframework.security.core.userdetails.User(username, password,
                auth);
    }

}
