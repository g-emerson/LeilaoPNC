/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pnc.leilaoVerde.dominio.entidades.Entidade;
import pnc.leilaoVerde.dominio.entidades.EstadoEntidade;

/**
 *
 * @author Vinicius
 */
public class ProporLeilaoControlTest {

    public ProporLeilaoControlTest() {
    }
    private ProporLeilaoControl control;

    @Before
    public void setUp() {
        Entidade entidade = new Entidade();

        entidade.setId(0L);
        entidade.setAdmin(false);
        entidade.setNome("Entidade 1");
        entidade.setStatus(EstadoEntidade.PENDENTE);
        entidade.setValidada(true);
        entidade.setQuantidadeCER(500);

        control = new ProporLeilaoControl(0L);
        control.setEntidade(entidade);
    }

    @Test
    public void testValidarInfoLeilaoNomeNulo() {
        control.setNomeLeilao(null);
        control.setLanceMinimo(200);
        control.setQuantCER(100);
        try {
            control.validarInfoLeilao();
            fail("Sucesso com nome leilao nulo");
        } catch (ProporLeilaoException ex) {
        }
    }

    @Test
    public void testValidarInfoLeilaoQtdCERNegativa() {
        control.setNomeLeilao("Leilao");
        control.setLanceMinimo(200);
        control.setQuantCER(-100);
        try {
            control.validarInfoLeilao();
            fail("Sucesso quant CER negativa");
        } catch (ProporLeilaoException ex) {
        }
    }

    @Test
    public void testValidarInfoLeilaoQtdCERMaiorQueDisponivel() {
        control.setNomeLeilao("Leilao");
        control.setLanceMinimo(200);
        control.setQuantCER(1000);

        try {
            control.validarInfoLeilao();
            fail("Sucesso quant CER maior que a disponivel na entidade");
        } catch (ProporLeilaoException ex) {
        }
    }

    @Test
    public void testValidarInfoLeilaoLanceMinimoNegativo() {
        control.setNomeLeilao("Leilao");
        control.setLanceMinimo(-200);
        control.setQuantCER(100);
        try {
            control.validarInfoLeilao();
            fail("Sucesso com lanceMinimo negativo");
        } catch (ProporLeilaoException ex) {
        }
    }

    @Test
    public void testValidarInfoLeilaoOK() {
        control.setNomeLeilao("Leilao");
        control.setLanceMinimo(200);
        control.setQuantCER(100);
        try {
            control.validarInfoLeilao();
        } catch (ProporLeilaoException ex) {
            fail("Falha com infos de leilao OK");
        }
    }
}
