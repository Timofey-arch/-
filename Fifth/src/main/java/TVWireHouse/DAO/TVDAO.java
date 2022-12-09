package TVWireHouse.DAO;

import TVWireHouse.Entities.TV;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class TVDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void add(TV tv) {
        entityManager.persist(tv);
    }

    public void update(TV tv){
        entityManager.merge(tv);
    }

    public void delete(TV tv){
        entityManager.remove(tv);
    }

    public TV findById(int id) {
        return entityManager.find(TV.class, id);
    }

    public List<TV> allTVs(){
        return entityManager.createQuery("from tv c order by c.id desc", TV.class).getResultList();
    }
}
