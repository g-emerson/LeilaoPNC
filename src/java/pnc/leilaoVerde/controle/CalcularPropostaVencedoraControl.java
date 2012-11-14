/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import pnc.leilaoVerde.dominio.EstadoLeilao;
import pnc.leilaoVerde.dominio.Leilao;
import pnc.util.DateUtil;

/**
 *
 * @author giovani
 */
public class CalcularPropostaVencedoraControl extends AbstractControl {

    private static final Logger logger =
            Logger.getLogger(CalcularPropostaVencedoraControl.class.getName());

    private EntityManagerFactory emf = null;

    public CalcularPropostaVencedoraControl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * Processa os leilões cujos horários de fim já foram alcançados.
     *
     * Leilões sem lances são finalizados e leilões com lances são postos no
     * estado EM_PAGAMENTO e passam a ser disponíveis para impressão do boleto
     * de pagamento.
     */
    public void calcularProposta() {
        EntityManager em = emf.createEntityManager();

        Calendar agora = DateUtil.getAgora();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        logger.log(Level.FINER, "Realizando um calculo: agora = {0}",
                df.format(agora.getTime()));

        try {
            em.getTransaction().begin();

            Query query = em.createNamedQuery("Leilao.findVencidos");

            query.setParameter("data", agora.getTime(), TemporalType.DATE);
            query.setParameter("hora", agora.getTime(), TemporalType.TIME);
            List<Leilao> lista = query.getResultList();

            logger.log(Level.FINER, "Número de leilões: {0}", lista.size());

            for (Leilao leilao : lista) {
                Calendar cal = DateUtil.getCalendarFromDateAndTime(
                        leilao.getDataFinal(), leilao.getHoraFinal());

                if (leilao.getEstado() != EstadoLeilao.EM_ANDAMENTO) {
                    continue;
                }

                if (agora.before(cal)) {
                    continue;
                }

                if (leilao.getMaiorLance() != null) {
                    leilao.setEstado(EstadoLeilao.EM_PAGAMENTO);
                } else {
                    leilao.setEstado(EstadoLeilao.CONCLUIDO);
                }
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            // e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
