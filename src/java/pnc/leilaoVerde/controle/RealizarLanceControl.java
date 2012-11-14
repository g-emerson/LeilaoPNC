/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

import pnc.leilaoVerde.dominio.LanceLeilaoException;
import pnc.leilaoVerde.dominio.Leilao;
import pnc.leilaoVerde.dominio.entidades.Entidade;

/**
 *
 * @author giovani
 */
public class RealizarLanceControl extends AbstractControl {
    private Leilao leilao;

    public RealizarLanceControl(Long leilaoId) {
        leilao = Leilao.carregarLeilao(leilaoId);
    }

    public Leilao getLeilao() {
        return leilao;
    }

    public void cadastrarLance(double valor, Entidade ent)
            throws LanceLeilaoException {
        leilao.cadastrarLance(valor, ent);
    }
}
