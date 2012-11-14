/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

import javax.persistence.EntityManager;
import pnc.leilaoVerde.dominio.EstadoLeilao;
import pnc.leilaoVerde.dominio.Leilao;

/**
 *
 * @author giovani
 */
public class GerenciarLeilaoControl extends AbstractControl {

    private Long leilaoId;
    private Leilao leilao;

    public GerenciarLeilaoControl(Long leilaoId) {
        this.leilaoId = leilaoId;
    }

    public Leilao getLeilao() {
        if (leilao == null) {
            EntityManager em = createEntityManager();

            leilao = em.find(Leilao.class, leilaoId);

            em.close();
        }

        return leilao;
    }

    public void removerLeilao() throws Exception {
        Leilao myleilao = getLeilao();

        EntityManager em = createEntityManager();

        try {
            myleilao = em.merge(myleilao);

            em.getTransaction().begin();

            em.remove(myleilao);

            em.getTransaction().commit();

            leilao = null;
        } catch (Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void alterarLeilao()  throws Exception {
        Leilao myleilao = getLeilao();

        EntityManager em = createEntityManager();

        try {
            em.getTransaction().begin();

            myleilao = em.merge(myleilao);

            if (myleilao.getEstado() == EstadoLeilao.PROPOSTO ) {
                myleilao.setEstado(EstadoLeilao.EM_ANDAMENTO);
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
