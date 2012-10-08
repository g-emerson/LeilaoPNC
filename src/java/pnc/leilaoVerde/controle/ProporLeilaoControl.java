/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import pnc.leilaoVerde.dominio.Leilao;
import pnc.leilaoVerde.dominio.entidades.Entidade;

/**
 * Classe de controle para o caso de uso Propor Leilao
 * @author giovani
 */
public class ProporLeilaoControl extends AbstractControl {

    private static final Logger logger = Logger.getLogger(ProporLeilaoControl.class.getName());
    private Long entId;
    private Entidade entidade = null;
    private int quantCER;
    private String nomeLeilao;
    private double lanceMinimo;

    public double getLanceMinimo() {
        return lanceMinimo;
    }

    public void setLanceMinimo(double lanceMinimo) {
        this.lanceMinimo = lanceMinimo;
    }

    public String getNomeLeilao() {
        return nomeLeilao;
    }

    public void setNomeLeilao(String nomeLeilao) {
        this.nomeLeilao = nomeLeilao;
    }

    public int getQuantCER() {
        return quantCER;
    }

    public void setQuantCER(int quantCER) {
        this.quantCER = quantCER;
    }

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

        if (ent != null) {
            return ent.isValidada();
        } else {
            return false;
        }
    }

    private void validarDados() throws ProporLeilaoException {
        if (getNomeLeilao() == null) {
            throw new ProporLeilaoException("Nome do leilao não fornecido");
        }

        if (getQuantCER() <= 0) {
            throw new ProporLeilaoException("Quantidade de CER ofertada não pode ser zero ou negativa");
        } else if (getQuantCER() > obterCERsDisponiveis()) {
            throw new ProporLeilaoException("Quantidade de CER ofertada maior que disponível");
        }

        if (getLanceMinimo() <= 0) {
            throw new ProporLeilaoException("Lance mínimo não pode ser zero ou negativa");
        }

        EntityManager em = createEntityManager();
        try {
            Leilao leilao = (Leilao) em.createNamedQuery("Leilao.findByName")
                .setParameter("nome", getNomeLeilao()).getSingleResult();

            if (leilao != null) {
                throw new ProporLeilaoException("Já existe leilão com este nome");
            }
        }
        catch (NoResultException e) {

        }
        finally {
            em.close();
        }
    }

    public void cadastrarProposta() throws ProporLeilaoException {
        validarDados();

        EntityManager em = createEntityManager();

        Entidade ent = getEntidade();
        try {
            em.getTransaction().begin();

            ent = em.merge(ent);

            Leilao leilao = ent.criarPropostaLeilao();

            leilao.setLanceMinimo(getLanceMinimo());
            leilao.setNomeLeilao(getNomeLeilao());
            leilao.setQuantidadeCER(getQuantCER());

            em.persist(leilao);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
