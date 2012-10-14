/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.dominio.entidades;

/**
 *
 * @author giovani
 */
public enum EstadoEntidade {

    PENDENTE {

        @Override
        public String toString() {
            return "Pendente";
        }
    },
    VALIDADA {

        @Override
        public String toString() {
            return "Reprovada";
        }
    },
    REPROVADA {

        @Override
        public String toString() {
            return "Reprovada";
        }
    }
}
