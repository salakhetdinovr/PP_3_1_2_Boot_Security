package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Transactional(readOnly=true)
    @Override
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }
    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

}