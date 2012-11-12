/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.dominio;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import pnc.leilaoVerde.dominio.entidades.Entidade;
import pnc.leilaoVerde.dominio.entidades.Lance;
import pnc.leilaoVerde.persistencia.Persistencia;
import pnc.util.DateUtil;

/**
 * Classe que representa um leilão disponibilizado por uma entidade para venda
 * de CER.
 *
 * @author giovani
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Leilao.findByName", query = "SELECT lei from Leilao lei WHERE lei.nomeLeilao = :nome"),
    @NamedQuery(name = "Leilao.findPropostos", query = "SELECT lei from Leilao lei"
    + " WHERE lei.estado = 'PROPOSTO'"
    + " ORDER BY lei.nomeLeilao"),
    @NamedQuery(name = "Leilao.findAtivos", query = "SELECT lei from Leilao lei"
    + " WHERE lei.estado != 'PROPOSTO'"
    + " ORDER BY lei.nomeLeilao"),
    @NamedQuery(name = "Leilao.findDaEntidade",
    query = "SELECT lei from Leilao lei WHERE lei.entidade.id = :entid"
    + " ORDER BY lei.nomeLeilao"),
    @NamedQuery(name = "Leilao.findAtivosDeOutrasEntidades",
    query = "SELECT lei from Leilao lei"
    + " WHERE lei.entidade.id != :entid and lei.estado = 'EM_ANDAMENTO'"
    + " ORDER BY lei.nomeLeilao"),
    @NamedQuery(name = "Leilao.findFinalizados",
    query = "SELECT lei FROM Leilao lei"
    + " WHERE lei.estado != 'PROPOSTO' AND lei.estado != 'EM_ANDAMENTO'")
})
public class Leilao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String nomeLeilao;
    @Column(nullable = false)
    private int quantidadeCER;
    @Column(nullable = false)
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
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoLeilao estado;
    @ManyToOne
    private Entidade entidade;
    @OneToOne
    private Lance maiorLance;

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

    public Lance getMaiorLance() {
        return maiorLance;
    }

    public void setMaiorLance(Lance maiorLance) {
        this.maiorLance = maiorLance;
    }

    public static List<Leilao> getLeiloesPropostos() {
        EntityManager em = Persistencia.getEntityManager();

        List<Leilao> list;

        Query query = em.createNamedQuery("Leilao.findPropostos");

        list = query.getResultList();

        em.close();

        return list;
    }

    public static List<Leilao> getLeiloesDaEntidade(Long entId) {
        EntityManager em = Persistencia.getEntityManager();

        List<Leilao> list;

        Query query = em.createNamedQuery("Leilao.findDaEntidade");

        query.setParameter("entid", entId);

        list = query.getResultList();

        em.close();

        return list;
    }

    public static List<Leilao> getLeiloesAprovados() {
        EntityManager em = Persistencia.getEntityManager();

        List<Leilao> list;

        Query query = em.createNamedQuery("Leilao.findAtivos");

        list = query.getResultList();

        em.close();

        return list;
    }

    public static List<Leilao> getLeiloesFinalizados() {
        EntityManager em = Persistencia.getEntityManager();

        List<Leilao> list;

        Query query = em.createNamedQuery("Leilao.findFinalizados");

        list = query.getResultList();

        em.close();

        return list;
    }

    public static List<Leilao> getLeiloesAtivosDeOutrasEntidades(Long entId) {
        EntityManager em = Persistencia.getEntityManager();

        List<Leilao> list;

        Query query = em.createNamedQuery("Leilao.findAtivosDeOutrasEntidades");

        query.setParameter("entid", entId);

        list = query.getResultList();

        em.close();

        return list;
    }

    public static Leilao carregarLeilao(Long leilaoId) {
        EntityManager em = Persistencia.getEntityManager();

        Leilao leilao;

        leilao = em.find(Leilao.class, leilaoId);

        em.close();

        return leilao;
    }

    public Calendar getDateTimeIncial() {
        return DateUtil.getCalendarFromDateAndTime(dataInicial, horaInicial);
    }

    public Calendar getDateTimeFinal() {
        return DateUtil.getCalendarFromDateAndTime(dataFinal, horaFinal);
    }

    public void cadastrarLance(double valor, Entidade ent)
            throws LanceLeilaoException {
        Date agora = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(agora);

        Calendar calIni = getDateTimeIncial();
        Calendar calFim = getDateTimeFinal();

        if (maiorLance != null && maiorLance.getValor() >= valor) {
            throw new LanceLeilaoException(String.format(
                    "Lance com valor menor ou igual ao maior lance: %.2f <= %.2f",
                    valor, maiorLance.getValor()));
        }

        if (cal.before(calIni)) {
            DateFormat df = DateFormat.getDateTimeInstance();
            throw new LanceLeilaoException("Lance antes do inicio do leilao: "
                    + String.format("Agora %s, inicio %s", df.format(agora),
                    df.format(calIni.getTime())));
        } else if (cal.after(calFim)) {
            DateFormat df = DateFormat.getDateTimeInstance();
            throw new LanceLeilaoException("Lance após p término do leilao: "
                    + String.format("Agora %s, término %s", df.format(agora),
                    df.format(calFim.getTime())));
        }

        if (valor < this.getLanceMinimo()) {
            throw new LanceLeilaoException("Valor do lance menor que lance mínimo");
        }

        EntityManager em = Persistencia.getEntityManager();

        try {
            em.getTransaction().begin();

            Lance lance = new Lance();

            em.persist(lance);

            lance.setHoraLance(agora);
            lance.setDataLance(agora);
            lance.setValor(valor);
            lance.setEntidade(ent);
            lance.setLeilao(this);

            this.setMaiorLance(lance);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new LanceLeilaoException(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
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
