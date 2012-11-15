/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

import java.util.Timer;
import java.util.TimerTask;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import pnc.leilaoVerde.persistencia.Persistencia;

/**
 * Web application lifecycle listener.
 * @author giovani
 */
public class WebAppListener implements ServletContextListener, ServletRequestListener {

    private EntityManagerFactory emf;
    private Timer timer = null;
    
    public void requestDestroyed(ServletRequestEvent sre) {
        Persistencia.removeEntityManager();
    }

    public void requestInitialized(ServletRequestEvent sre) {
    }

    public void contextInitialized(ServletContextEvent sce) {
        emf = Persistence.createEntityManagerFactory("LeilaoPNCPU");
        Persistencia.setEntityManagerFactory(emf);

        CalcularPropostaVencedoraControl calculoCtrl = new CalcularPropostaVencedoraControl(emf);
        TimerTask timerTask = new CalcularPropostaTimerTask(calculoCtrl);

        timer = new Timer();

        timer.schedule(timerTask, 60000, 60000);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        emf.close();
        timer.cancel();
    }
}
