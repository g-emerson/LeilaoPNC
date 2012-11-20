/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.dominio;

import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pnc.leilaoVerde.dominio.entidades.Lance;

/**
 *
 * @author Vinicius
 */
public class LeilaoTest {

    public LeilaoTest() {
    }
    private Calendar bemAntes;
    private Calendar antes;
    private Calendar agora;
    private Calendar depois;
    private Leilao leilao;

    @Before
    public void setUp() {
        agora = Calendar.getInstance();
        agora.set(2012, Calendar.JULY, 7, 7, 30, 10);
        bemAntes = Calendar.getInstance();
        bemAntes.set(2012, Calendar.JANUARY, 1, 8, 30, 10);
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

    @Test
    public void test_ValidarLanceValorNaoMaior() throws Exception {
        Lance lance = new Lance();

        lance.setValor(300);
        lance.setDataLance(agora.getTime());
        lance.setHoraLance(agora.getTime());
        lance.setLeilao(leilao);

        leilao.setMaiorLance(lance);
        leilao.setEstado(EstadoLeilao.EM_ANDAMENTO);

        try {
            leilao.validarLance(agora.getTime(), 220);
            fail("Sucesso com valor menor que melhor lance");
        } catch (LanceLeilaoException e) {
        }
    }
    
    @Test
    public void test_ValidarLanceLeilaoVencido() throws Exception {
        leilao.setEstado(EstadoLeilao.EM_ANDAMENTO);
        leilao.setDataInicial(bemAntes.getTime());
        leilao.setDataFinal(antes.getTime());

        try {
            leilao.validarLance(agora.getTime(), 220);
            fail("Sucesso com lance apos termino do leilao");
        } catch (LanceLeilaoException e) {
        }
    }

    @Test
    public void test_ValidarLanceAntesDoInicio() throws Exception {
        leilao.setEstado(EstadoLeilao.EM_ANDAMENTO);
        leilao.setDataInicial(antes.getTime());
        leilao.setDataFinal(agora.getTime());

        try {
            leilao.validarLance(bemAntes.getTime(), 220);
            fail("Sucesso com lance antes do inicio do leilao");
        } catch (LanceLeilaoException e) {
        }
    }
    
    @Test
    public void test_ValidarLanceOK() throws Exception {
        leilao.setEstado(EstadoLeilao.EM_ANDAMENTO);
        leilao.setDataInicial(antes.getTime());
        leilao.setDataFinal(depois.getTime());

        try {
            leilao.validarLance(agora.getTime(), 220);
        } catch (LanceLeilaoException e) {
            fail("Falha na verificacao de lance valido");
        }
    }

}
