/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.dominio.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import pnc.leilaoVerde.dominio.EstadoLeilao;
import pnc.leilaoVerde.dominio.Leilao;
import pnc.leilaoVerde.dominio.administrativo.Usuario;

/**
 *
 * @author giovani
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Entidade.findAll", query="SELECT ent from Entidade ent")
})
public class Entidade extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(length = 64)
    private String nome;
    @Column(length = 15)
    private String cnpj;
    @Column
    private int quantidadeCER;
    @Column
    private boolean validada;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeCER() {
        return quantidadeCER;
    }

    public void setQuantidadeCER(int quantidadeCER) {
        this.quantidadeCER = quantidadeCER;
    }

    public boolean isValidada() {
        return validada;
    }

    public void setValidada(boolean validada) {
        this.validada = validada;
    }

    /**
     * Obtem a quantidade de CERs disponivies para a entidade
     * fornecida oferecer em leilões.
     * 
     * @return Quantidade de CERs disponíveis
     */
    public int obterCERsDisponiveis() {
        // TODO: Propor Leilão - Deve recuperar a quantidade de CREs que possui e que ainda não
        // foram ofertadas em nenhum leilão.
        // Por enquanto retornaremos o total de CER que possui
        return getQuantidadeCER();
    }

    public Leilao criarPropostaLeilao() {
        Leilao leilao = new Leilao();

        leilao.setEntidade(this);
        leilao.setEstado(EstadoLeilao.PROPOSTO);

        return leilao;
    }

    @Override
    public String toString() {
        return "pnc.dominio.entidades.Entidade[ id=" + getId() + " ]";
    }
}
