/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.dominio;

/**
 *
 * @author giovani
 */
public enum EstadoLeilao {

    PROPOSTO("Proposto"),
    CANCELADO("Cancelado"),
    EM_ANDAMENTO("Em andamento"),
    CONCLUIDO("Concluído"),
    EM_PAGAMENTO("Em pagamento");

    private String desc;

    private EstadoLeilao(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return desc;
    }
}
