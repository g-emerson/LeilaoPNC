/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pnc.leilaoVerde.dominio.entidades.Entidade;

/**
 * Classe de controle para o caso de uso Propor Leilao
 * @author giovani
 */
public class ProporLeilaoControl {
    /**
     * Obtem a quantidade de CERs disponivies para a entidade
     * fornecida oferecer em leilões.
     * 
     * @param entId Id da entidade
     * @return Quantidade de CERs disponíveis
     */
    public int obterCERsDisponiveis(Long entId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LeilaoPNCPU");
        
        EntityManager em = emf.createEntityManager();
        
        Entidade ent = em.find(Entidade.class, entId);
        
        if (ent != null) {
            return ent.obterCERsDisponiveis(entId);
        }
        else {
            return 0;
        }
    }
}
