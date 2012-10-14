/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

import javax.persistence.EntityManager;
import pnc.leilaoVerde.dominio.entidades.Entidade;
import pnc.leilaoVerde.dominio.entidades.EstadoEntidade;

/**
 *
 * @author giovani
 */
public class ValidarEntidadeControl extends AbstractControl {

    public EstadoEntidade validarEntidade(Long entId, boolean validada) {
        EntityManager em = createEntityManager();
        Entidade ent = null;
        
        em.getTransaction().begin();
        ent = em.find(Entidade.class, entId);
        ent.setValidada(validada);
        em.getTransaction().commit();
        em.close();

        return ent.getStatus();
    }
}
