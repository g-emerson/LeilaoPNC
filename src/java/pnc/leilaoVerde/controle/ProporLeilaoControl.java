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

    private Long entId;
    private Entidade entidade = null;
    private EntityManager entityManager = null;

    public ProporLeilaoControl(Long entId) {
        this.entId = entId;
    }

    private Entidade getEntidade() {
        if (entidade == null) {
            EntityManager em = getEntityManager();
            
            entidade = em.find(Entidade.class, entId);
        }

        return entidade;
    }

    private EntityManager getEntityManager() {
        if (entityManager == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("LeilaoPNCPU");

            entityManager = emf.createEntityManager();
        }

        return entityManager;
    }

    /**
     * Obtem a quantidade de CERs disponivies para a entidade
     * fornecida oferecer em leilões.
     * 
     * @param entId Id da entidade
     * @return Quantidade de CERs disponíveis
     */
    public int obterCERsDisponiveis() {
        Entidade ent = getEntidade();

        if (ent != null) {
            return ent.obterCERsDisponiveis();
        } else {
            return 0;
        }
    }

    public boolean isEntidadeValidada() {
        Entidade ent = getEntidade();

        if ( ent != null ) {
            return ent.isValidada();
        }
        else {
            return false;
        }
    }

    public void liberarRecursos() {
        if (entidade != null) {
            entidade = null;
        }
        if ( entityManager != null ) {
            entityManager.close();
            entityManager = null;
        }
    }
}
