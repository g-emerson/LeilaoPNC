/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.persistencia;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;

/**
 * EntityManager com fechamento (close) tardio.
 * 
 * Engloba um EntityManager passado na construção do objeto e delega
 * a implementação dos métodos a esse objeto. Apenas o método colse não e'
 * executado imediatamente quando chamado. O objeto real será fechado apenas
 * quando o método realClose for chamado.
 * 
 * @author giovani
 */
public class LazyCloseEntityManager implements EntityManager {

    private EntityManager em;
    private static final Logger logger =
            Logger.getLogger(LazyCloseEntityManager.class.getName());
    private static int novoID = 0;
    private int id;

    public LazyCloseEntityManager(EntityManager em) {
        this.em = em;

        id = ++novoID;

        logger.log(Level.FINER, "EntityManager criado:{0}", id);
    }

    public void realClose() {
        if (em.isOpen()) {
            em.close();
        }
        logger.log(Level.FINER, "EntityManager fechado: {0}", id);
    }

    public void persist(Object o) {
        em.persist(o);
    }

    public <T> T merge(T t) {
        return em.merge(t);
    }

    public void remove(Object o) {
        em.remove(o);
    }

    public <T> T find(Class<T> type, Object o) {
        return em.find(type, o);
    }

    public <T> T getReference(Class<T> type, Object o) {
        return em.getReference(type, o);
    }

    public void flush() {
        em.flush();
    }

    public void setFlushMode(FlushModeType fmt) {
        em.setFlushMode(fmt);
    }

    public FlushModeType getFlushMode() {
        return em.getFlushMode();
    }

    public void lock(Object o, LockModeType lmt) {
        em.lock(o, lmt);
    }

    public void refresh(Object o) {
        em.refresh(o);
    }

    public void clear() {
        em.clear();
    }

    public boolean contains(Object o) {
        return em.contains(o);
    }

    public Query createQuery(String string) {
        return em.createQuery(string);
    }

    public Query createNamedQuery(String string) {
        return em.createNamedQuery(string);
    }

    public Query createNativeQuery(String string) {
        return em.createNativeQuery(string);
    }

    public Query createNativeQuery(String string, Class type) {
        return em.createNativeQuery(string, type);
    }

    public Query createNativeQuery(String string, String string1) {
        return em.createNativeQuery(string, string1);
    }

    public void joinTransaction() {
        em.joinTransaction();
    }

    public Object getDelegate() {
        return em.getDelegate();
    }

    public void close() {
    }

    public boolean isOpen() {
        return em.isOpen();
    }

    public EntityTransaction getTransaction() {
        return em.getTransaction();
    }
}
