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
    PROPOSTO {

        @Override
        public String toString() {
            return "Proposto";
        }
    },
    CANCELADO {

        @Override
        public String toString() {
            return "Cancelado";
        }
    },
    EM_ANDAMENTO {

        @Override
        public String toString() {
            return "Em andamento";
        }
    },
    CONCLUIDO {

        @Override
        public String toString() {
            return "Concluído";
        }
    },
    EM_PAGAMENTO {

        @Override
        public String toString() {
            return "Em pagamento";
        }
    }
}
