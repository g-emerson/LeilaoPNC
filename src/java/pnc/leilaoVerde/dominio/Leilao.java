/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.dominio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import pnc.leilaoVerde.dominio.entidades.Entidade;

/**
 *
 * @author giovani
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Leilao.findByName", query="SELECT lei from Leilao lei where lei.nomeLeilao = :nome"),
    @NamedQuery(name="Leilao.findPropostos", query="SELECT lei from Leilao lei where lei.estado = 'PROPOSTO'"),
    @NamedQuery(name="Leilao.findAtivos", query="SELECT lei from Leilao lei where lei.estado != 'PROPOSTO'"),
    @NamedQuery(name="Leilao.findDaEntidade",
        query="SELECT lei from Leilao lei where lei.entidade.id = :entid"),
    @NamedQuery(name="Leilao.findAtivosDeOutrasEntidades", query="SELECT lei from Leilao lei where lei.entidade.id != :entid")
})
public class Leilao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true, nullable=false)
    private String nomeLeilao;

    @Column(nullable=false)
    private int quantidadeCER;

    @Column(nullable=false)
    private double lanceMinimo;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dataInicial;

    @Column
    @Temporal(TemporalType.TIME)
    private Date horaInicial;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dataFinal;

    @Column
    @Temporal(TemporalType.TIME)
    private Date horaFinal;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private EstadoLeilao estado;

    @ManyToOne
    private Entidade entidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Date horaFinal) {
        this.horaFinal = horaFinal;
    }

    public Date getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(Date horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getNomeLeilao() {
        return nomeLeilao;
    }

    public void setNomeLeilao(String nomeLeilao) {
        this.nomeLeilao = nomeLeilao;
    }

    public int getQuantidadeCER() {
        return quantidadeCER;
    }

    public void setQuantidadeCER(int quantidadeCER) {
        this.quantidadeCER = quantidadeCER;
    }

    public EstadoLeilao getEstado() {
        return estado;
    }

    public void setEstado(EstadoLeilao estado) {
        this.estado = estado;
    }

    public double getLanceMinimo() {
        return lanceMinimo;
    }

    public void setLanceMinimo(double lanceMinimo) {
        this.lanceMinimo = lanceMinimo;
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Leilao)) {
            return false;
        }
        Leilao other = (Leilao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pnc.leilaoVerde.dominio.Leilao[ id=" + id + " ]";
    }
}
