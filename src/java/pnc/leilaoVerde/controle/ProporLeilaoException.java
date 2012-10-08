/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

/**
 *
 * @author giovani
 */
public class ProporLeilaoException extends Exception {

    public ProporLeilaoException(Throwable thrwbl) {
        super(thrwbl);
    }

    public ProporLeilaoException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public ProporLeilaoException(String string) {
        super(string);
    }

    public ProporLeilaoException() {
    }
}
