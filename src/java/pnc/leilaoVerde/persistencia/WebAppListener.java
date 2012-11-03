/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.persistencia;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * Web application lifecycle listener.
 * @author giovani
 */
public class WebAppListener implements ServletContextListener, ServletRequestListener {

    private EntityManagerFactory emf;
    
    public void requestDestroyed(ServletRequestEvent sre) {
        Persistencia.removeEntityManager();
    }

    public void requestInitialized(ServletRequestEvent sre) {
    }

    public void contextInitialized(ServletContextEvent sce) {
        emf = Persistence.createEntityManagerFactory("LeilaoPNCPU");
        Persistencia.setEntityManagerFactory(emf);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        emf.close();
    }
}
