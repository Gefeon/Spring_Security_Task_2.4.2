package mvc.service;

import mvc.model.Role;
import java.util.List;

public interface RoleService {

    void addRole(Role role);

    void editRole(Role role);

    void deleteRole(long id);

    List<Role> listRoles();

    Role getRoleByName(String name);
}