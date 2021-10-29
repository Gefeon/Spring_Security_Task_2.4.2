package mvc.dao;

import mvc.model.Role;
import java.util.List;

public interface RoleDao {

    void addRole(Role role);

    void editRole(Role role);

    void deleteRole(long id);

    List<Role> listRoles();

    Role getRoleByName(String name);
}
