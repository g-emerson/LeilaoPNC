/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.dominio.entidades;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pnc.leilaoVerde.dominio.EstadoLeilao;
import pnc.leilaoVerde.dominio.Leilao;

/**
 *
 * @author Vinicius
 */
public class EntidadeTest {
    
    public EntidadeTest() {
    }
    
    private Entidade entidade;
    
    @Before
    public void setUp() {
        entidade = new Entidade();
        
        entidade.setId(0L);
        entidade.setAdmin(false);
        entidade.setNome("Entidade 1");
        entidade.setStatus(EstadoEntidade.PENDENTE);
    }

    @Test
    public void testIsValidada() {
        entidade.setStatus(EstadoEntidade.PENDENTE);
        assertFalse(entidade.isValidada());
        
        entidade.setValidada(true);
        assertTrue(entidade.isValidada());
        assertEquals(EstadoEntidade.VALIDADA, entidade.getStatus());
        
        entidade.setValidada(false);
        assertFalse(entidade.isValidada());
        assertEquals(EstadoEntidade.REPROVADA, entidade.getStatus());
    }
    
    @Test
    public void testCriarPropostaLeilaoEntidadeInvalida() {
        entidade.setValidada(false);
        
        Leilao leilao  = entidade.criarPropostaLeilao();
        
        assertEquals(null, leilao);
    }
    
    @Test
    public void testCriarPropostaLeilaoEntidadeValida() {
        entidade.setValidada(true);
        
        Leilao leilao  = entidade.criarPropostaLeilao();
        
        assertTrue(leilao != null);
        assertEquals(EstadoLeilao.PROPOSTO, leilao.getEstado());
        assertEquals(entidade, leilao.getEntidade());
    }
}
