/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.controle;

import java.util.List;
import pnc.leilaoVerde.dominio.Leilao;

/**
 *
 * @author giovani
 */
public class ListarLeiloesControl extends AbstractControl {

    public List<Leilao> obterLeiloesPropostos() {
        return Leilao.getLeiloesPropostos();
    }

    public List<Leilao> obterLeiloesAtivos() {
        return Leilao.getLeiloesAprovados();
    }

    public List<Leilao> obterLeiloesDaEntidade(Long idEnt) {
        return Leilao.getLeiloesDaEntidade(idEnt);
    }
    
    public List<Leilao> obterLeiloesFinalizados() {
        return Leilao.getLeiloesFinalizados();
    }
    
    public List<Leilao> obterLeiloesDeOutrasEntidades(Long idEnt) {
        return Leilao.getLeiloesAtivosDeOutrasEntidades(idEnt);
    }
}
