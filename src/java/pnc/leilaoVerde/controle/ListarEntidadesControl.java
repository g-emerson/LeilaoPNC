/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import pnc.leilaoVerde.dominio.entidades.Entidade;
import pnc.leilaoVerde.dominio.entidades.EstadoEntidade;

/**
 *
 * @author giovani
 */
public class ListarEntidadesControl extends AbstractControl {

    public List<Entidade> obterListaDeEntidades() {
        List<Entidade> list;

        EntityManager em = createEntityManager();

        Query query = em.createNamedQuery("Entidade.findAll");

        list = query.getResultList();

        em.close();

        return list;
    }

    public List<Entidade> getEntidadesPeloStatus(EstadoEntidade status) {
        List<Entidade> list = new ArrayList<Entidade>();

        EntityManager em = createEntityManager();

        Query query = em.createNamedQuery("Entidade.findPeloStatus");
        query.setParameter("status", status);
        list = query.getResultList();

        em.close();

        return list;
    }

    public List<Entidade> getRankingDeEntidades() {
        List<Entidade> list = new ArrayList<Entidade>();

        EntityManager em = createEntityManager();

        Query query = em.createNamedQuery("Entidade.getRanking");
        query.setParameter("status", EstadoEntidade.VALIDADA);
        list = query.getResultList();

        em.close();

        return list;
    }
}
