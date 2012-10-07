/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

import javax.persistence.EntityManager;
import pnc.leilaoVerde.dominio.entidades.Entidade;

/**
 *
 * @author giovani
 */
public class ValidarEntidadeControl extends AbstractControl {

    public void validarEntidade(Long entId) {
        EntityManager em = createEntityManager();
        Entidade ent = null;

        em.getTransaction().begin();
        ent = em.find(Entidade.class, entId);
        ent.setValidada(true);
        em.getTransaction().commit();
        em.close();
    }
}
