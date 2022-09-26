package TVWireHouse.DAO;

import TVWireHouse.Entities.TV;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;

public class TVDAO {
    private SessionFactory session;

    public void add(TV tv) {
        Transaction addTransaction = null;
        try (Session addSession = this.session.openSession()) {
            addTransaction = addSession.beginTransaction();
            addSession.save(tv);
            addTransaction.commit();
            addSession.close();
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
            if (addTransaction != null) {
                addTransaction.rollback();
            }
        }
    }

    public void update(TV tv){
        Transaction updateTransaction = null;
        try (Session updateSession = this.session.openSession()) {
            updateTransaction = updateSession.beginTransaction();
            updateSession.update(tv);
            updateTransaction.commit();
            updateSession.close();
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
            if (updateTransaction != null) {
                updateTransaction.rollback();
            }
        }
    }

    public void delete(TV tv){
        Transaction deleteTransaction = null;
        try (Session deleteSession = this.session.openSession()) {
            deleteTransaction = deleteSession.beginTransaction();
            deleteSession.delete(tv);
            deleteTransaction.commit();
            deleteSession.close();
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
            if (deleteTransaction != null) {
                deleteTransaction.rollback();
            }
        }
    }

    public TV findById(int id) {
        Transaction finByIdTransaction = null;
        TV tv;
        try (Session findByIdSession = this.session.openSession()) {
            finByIdTransaction = findByIdSession.beginTransaction();
            tv = findByIdSession.get(TV.class, id);
            if(tv == null){
                finByIdTransaction.commit();
                findByIdSession.close();
                return null;
            }
            finByIdTransaction.commit();
            findByIdSession.close();
        } catch (HibernateException hibernateException) {
            hibernateException.printStackTrace();
            if (finByIdTransaction != null) {
                finByIdTransaction.rollback();
            }
            return null;
        }
        return tv;
    }

    public LinkedList<TV> allTVs(){
        return (LinkedList<TV>) this.session.openSession().createQuery("From TV ").list();
    }

    public SessionFactory getSession() {
        return session;
    }

    @Autowired
    public void setSession(SessionFactory session) {
        this.session = session;
    }
}
