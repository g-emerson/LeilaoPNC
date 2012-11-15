/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.dominio;

/**
 *
 * @author giovani
 */
public class LanceLeilaoException extends Exception {

    public LanceLeilaoException(Throwable thrwbl) {
        super(thrwbl);
    }

    public LanceLeilaoException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public LanceLeilaoException(String string) {
        super(string);
    }

    public LanceLeilaoException() {
    }
}
