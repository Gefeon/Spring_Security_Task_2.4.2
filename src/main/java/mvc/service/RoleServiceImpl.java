package mvc.service;

import mvc.dao.RoleDao;
import mvc.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    @Transactional
    public void editRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    @Transactional
    public void deleteRole(long id) {
        roleDao.deleteRole(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> listRoles() {
        return roleDao.listRoles();
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }
}
