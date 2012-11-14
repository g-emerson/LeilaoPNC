/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

import java.util.TimerTask;

/**
 *
 * @author giovani
 */
public class CalcularPropostaTimerTask extends TimerTask {

    private CalcularPropostaVencedoraControl control;

    public CalcularPropostaTimerTask(CalcularPropostaVencedoraControl control) {
        this.control = control;
    }

    @Override
    public void run() {
        control.calcularProposta();
    }

}
