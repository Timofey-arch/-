package TVWireHouse.DAO;

import TVWireHouse.Entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void add(User user) {
        entityManager.persist(user);
    }

    public void update(User user){
        entityManager.merge(user);
    }

    public void delete(User user){
        entityManager.remove(user);
    }

    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    public List<User> allUsers(){
        return entityManager.createQuery("from users c order by c.id desc", User.class).getResultList();
    }

    public boolean userExist(String username){
        List<User> userList = this.allUsers();

        for (User user: userList) {
            if(user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public User findByUsername(String username){
        List<User> userList = this.allUsers();

        for (User user: userList) {
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
}
