package mvc.dao;

import mvc.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        entityManager.createQuery("DELETE FROM User user WHERE user.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("SELECT users FROM User users", User.class).getResultList();
    }

    @Override
    public User getUserByUsername(String username) {
        return entityManager.createQuery("select u from User u where u.username=:username",
                User.class).setParameter("username", username).getSingleResult();
    }
}