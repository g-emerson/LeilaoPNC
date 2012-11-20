/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.dominio.administrativo;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vinicius
 */
public class UsuarioTest {
    
    public UsuarioTest() {
    }

    @Test
    public void test_autenticar() {
        Usuario usu = new Usuario();
        
        usu.setEmail("user");
        usu.setSenha("123");
        
        assertEquals(false, usu.autenticar("321"));
        assertEquals(true, usu.autenticar("123"));
    }
}
