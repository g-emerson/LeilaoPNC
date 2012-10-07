/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

import javax.persistence.EntityManager;
import pnc.leilaoVerde.dominio.entidades.Entidade;

/**
 * Classe de controle para o caso de uso Propor Leilao
 * @author giovani
 */
public class ProporLeilaoControl extends AbstractControl {

    private Long entId;
    private Entidade entidade = null;

    public ProporLeilaoControl(Long entId) {
        this.entId = entId;
    }

    private Entidade getEntidade() {
        if (entidade == null) {
            EntityManager em = createEntityManager();
            
            entidade = em.find(Entidade.class, entId);

            em.close();
        }

        return entidade;
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
}
