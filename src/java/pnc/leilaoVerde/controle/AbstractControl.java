/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

import javax.persistence.EntityManager;
import pnc.leilaoVerde.persistencia.Persistencia;

/**
 *
 * @author giovani
 */
public abstract class AbstractControl {
    protected EntityManager createEntityManager() {
        return Persistencia.getEntityManager();
    }
}
