/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import pnc.leilaoVerde.dominio.administrativo.Usuario;

/**
 *
 * @author giovani
 */
public class LoginControl extends AbstractControl {

    private static final String USU_ADMIN = "admin";
    private Usuario usuario = null;

    /**
     * @return O usuario que foi autenticado
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Autentica o usuário fornecido no sistema
     *
     * @param login Login do usuário
     * @param senha Senha do usuário
     * @return true - Usuário autenticado, false caso contrário
     */
    public boolean autenticarUsuario(String login, String senha) {
        boolean autenticado = false;
        Usuario usu = null;
        EntityManager em = null;

        try {
            em = createEntityManager();

            usu = (Usuario) em.createNamedQuery("Usuario.findByEmail").setParameter("email", login).getSingleResult();
        } catch (Exception e) {
            String msg = String.format("Login %s senha %s\n %s \n", login, senha, e.getMessage());
            usu = null;

            Logger log = Logger.getAnonymousLogger();
            log.severe(msg);
        } finally {
            if (em != null) {
                em.close();
                em = null;
            }
        }

        if (usu != null) {
            autenticado = usu.autenticar(senha);
        }
        else if (login.equals(USU_ADMIN)) {
            usu = new Usuario();

            usu.setAdmin(true);
            usu.setEmail(login);
            usu.setSenha(senha);

            try {
                em = createEntityManager();
                em.getTransaction().begin();
                em.persist(usu);
                em.getTransaction().commit();

                autenticado = true;
            } finally {
                if (em != null) {
                    em.close();
                    em = null;
                }
            }
        }

        if (autenticado) {
            usuario = usu;
        } else {
            usuario = null;
        }

        return autenticado;
    }
}
