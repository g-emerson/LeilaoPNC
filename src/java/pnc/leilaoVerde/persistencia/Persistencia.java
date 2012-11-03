/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author giovani
 */
public class Persistencia  {
    private static final ThreadLocal<LazyCloseEntityManager> threadLocalEM =
            new ThreadLocal<LazyCloseEntityManager>();
    
    private static EntityManagerFactory emf;
    
    public static void setEntityManagerFactory(EntityManagerFactory emf) {
        Persistencia.emf = emf;
    }
    
    public static EntityManager getEntityManager() {
        LazyCloseEntityManager em = threadLocalEM.get();
        
        if ( em == null ) {
            EntityManager em1 = emf.createEntityManager();
            em = new LazyCloseEntityManager(em1);
            
            threadLocalEM.set(em);
        }
        
        return em;
    }
    
    public static void removeEntityManager() {
        LazyCloseEntityManager em = threadLocalEM.get();
        
        if ( em != null ) {
            em.realClose();
            
            threadLocalEM.remove();
        }        
    }
}
