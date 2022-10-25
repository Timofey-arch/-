package TVWireHouse.DAO;

import TVWireHouse.Entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDAO {
    private SessionFactory session;

    public void add(User user) {
        Transaction addTransaction = null;
        try (Session addSession = this.session.openSession()) {
            addTransaction = addSession.beginTransaction();
            addSession.save(user);
            addTransaction.commit();
            addSession.close();
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
            if (addTransaction != null) {
                addTransaction.rollback();
            }
        }
    }

    public void update(User user){
        Transaction updateTransaction = null;
        try (Session updateSession = this.session.openSession()) {
            updateTransaction = updateSession.beginTransaction();
            updateSession.update(user);
            updateTransaction.commit();
            updateSession.close();
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
            if (updateTransaction != null) {
                updateTransaction.rollback();
            }
        }
    }

    public boolean delete(int id){
        Transaction deleteTransaction = null;
        User user;
        try (Session deleteSession = this.session.openSession()) {
            deleteTransaction = deleteSession.beginTransaction();
            user = this.findById(id);
            if(user == null){
                deleteTransaction.commit();
                deleteSession.close();
                return false;
            }
            deleteSession.delete(user);
            deleteTransaction.commit();
            deleteSession.close();
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
            if (deleteTransaction != null) {
                deleteTransaction.rollback();
            }
        }
        return true;
    }

    public User findById(int id) {
        Transaction finByIdTransaction = null;
        User user;
        try (Session findByIdSession = this.session.openSession()) {
            finByIdTransaction = findByIdSession.beginTransaction();
            user = findByIdSession.get(User.class, id);
            if(user == null){
                finByIdTransaction.commit();
                findByIdSession.close();
                return null;
            }
            finByIdTransaction.commit();
            findByIdSession.close();
            return user;
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
            if (finByIdTransaction != null) {
                finByIdTransaction.rollback();
            }
            return null;
        }
    }

    public List<User> allUsers(){
        try (Session selectSession = this.session.openSession()){
            CriteriaBuilder criteriaBuilder = selectSession.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            criteriaQuery.select(userRoot).where();

            Query selectQuery = selectSession.createQuery(criteriaQuery);
            return (List<User>) selectQuery.getResultList();
        }catch (HibernateException exception){
            exception.printStackTrace();
            return null;
        }
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

    public SessionFactory getSession() {
        return session;
    }

    @Autowired
    public void setSession(SessionFactory session) {
        this.session = session;
    }
}
