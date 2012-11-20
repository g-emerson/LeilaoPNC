/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import pnc.leilaoVerde.dominio.EstadoLeilao;
import pnc.leilaoVerde.dominio.Leilao;
import pnc.leilaoVerde.dominio.entidades.Lance;

/**
 *
 * @author Vinicius
 */
public class CalcularPropostaVencedoraControlTest {

    public CalcularPropostaVencedoraControlTest() {
    }
    private static CalcularPropostaVencedoraControl control;
    private List<Leilao> lista;
    private Calendar antes;
    private Calendar agora;
    private Calendar depois;
    private Leilao leilao;

    @BeforeClass
    public static void setUpClass() {
        control = new CalcularPropostaVencedoraControl(null);
    }

    @Before
    public void beforeTest() {
        lista = new ArrayList<Leilao>();

        agora = Calendar.getInstance();
        agora.set(2012, Calendar.JULY, 7, 7, 30, 10);
        antes = Calendar.getInstance();
        antes.set(2012, Calendar.JANUARY, 7, 12, 30, 10);
        depois = Calendar.getInstance();
        depois.set(2012, Calendar.DECEMBER, 7, 18, 30, 10);

        leilao = new Leilao();

        leilao.setId(0L);
        leilao.setDataFinal(depois.getTime());
        leilao.setHoraFinal(depois.getTime());
        leilao.setDataInicial(antes.getTime());
        leilao.setHoraInicial(antes.getTime());
        leilao.setLanceMinimo(200);
        leilao.setNomeLeilao("Leilao Teste");
        leilao.setQuantidadeCER(200);
        leilao.setEstado(EstadoLeilao.PROPOSTO);
    }

    /**
     * Test of processarListaLeiloes method, of class
     * CalcularPropostaVencedoraControl.
     */
    @Test
    public void test_processarListaLeiloesLeilaoNaoEmAndamento() {
        leilao.setEstado(EstadoLeilao.PROPOSTO);
        lista.add(leilao);
        
        control.processarListaLeiloes(lista, agora);
        assertEquals(EstadoLeilao.PROPOSTO, leilao.getEstado());

        leilao.setEstado(EstadoLeilao.EM_PAGAMENTO);
        control.processarListaLeiloes(lista, agora);
        assertEquals(EstadoLeilao.EM_PAGAMENTO, leilao.getEstado());

        leilao.setEstado(EstadoLeilao.CONCLUIDO);
        control.processarListaLeiloes(lista, agora);
        assertEquals(EstadoLeilao.CONCLUIDO, leilao.getEstado());

        leilao.setEstado(EstadoLeilao.CANCELADO);
        control.processarListaLeiloes(lista, agora);
        assertEquals(EstadoLeilao.CANCELADO, leilao.getEstado());
    }
    
    @Test
    public void test_processarListaLeiloesLeilaoNaoVencido() {
        leilao.setEstado(EstadoLeilao.EM_ANDAMENTO);
        leilao.setDataFinal(depois.getTime());
        
        lista.add(leilao);
        control.processarListaLeiloes(lista, agora);
        assertEquals(EstadoLeilao.EM_ANDAMENTO, leilao.getEstado());
    }
    
    @Test
    public void test_processarListaLeiloesLeilaoVencidoSemLance() {
        leilao.setEstado(EstadoLeilao.EM_ANDAMENTO);
        leilao.setDataFinal(antes.getTime());
        leilao.setMaiorLance(null);
        
        lista.add(leilao);
        control.processarListaLeiloes(lista, agora);
        assertEquals(EstadoLeilao.CONCLUIDO, leilao.getEstado());
    }
    
    @Test
    public void test_processarListaLeiloesLeilaoVencidoComLance() {
        leilao.setEstado(EstadoLeilao.EM_ANDAMENTO);
        leilao.setDataFinal(antes.getTime());
        
        Lance lance = new Lance();
        lance.setLeilao(leilao);
        lance.setValor(300);
        
        leilao.setMaiorLance(lance);
        
        lista.add(leilao);
        control.processarListaLeiloes(lista, agora);
        assertEquals(EstadoLeilao.EM_PAGAMENTO, leilao.getEstado());
    }
}
