/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author giovani
 */
public abstract class AbstractControl {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("LeilaoPNCPU");

    public boolean persist(Object object) {
        boolean persistiu = false;
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
            persistiu = true;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return persistiu;
    }

    protected EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    protected EntityManager createEntityManager() {
        return emf.createEntityManager();
    }
}
