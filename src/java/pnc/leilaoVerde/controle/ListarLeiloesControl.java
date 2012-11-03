/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import pnc.leilaoVerde.dominio.Leilao;

/**
 *
 * @author giovani
 */
public class ListarLeiloesControl extends AbstractControl {

    public List<Leilao> obterLeiloesPropostos() {
        List<Leilao> list;

        EntityManager em = createEntityManager();

        Query query = em.createNamedQuery("Leilao.findPropostos");

        list = query.getResultList();

        em.close();

        return list;
    }

    public List<Leilao> obterLeiloesAtivos() {
        List<Leilao> list;

        EntityManager em = createEntityManager();

        Query query = em.createNamedQuery("Leilao.findAtivos");

        list = query.getResultList();

        em.close();

        return list;
    }

    public List<Leilao> obterLeiloesDaEntidade(Long idEnt) {
        List<Leilao> list;

        EntityManager em = createEntityManager();

        Query query = em.createNamedQuery("Leilao.findAtivos");

        list = query.getResultList();

        em.close();

        return list;
    }
}
