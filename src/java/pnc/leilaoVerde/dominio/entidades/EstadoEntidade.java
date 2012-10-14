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
    PENDENTE("Pendente"),
    VALIDADA("Validada"),
    REPROVADA("Reprovada");
    
    private final String texto;

    private EstadoEntidade(String texto) {
        this.texto = texto;
    }
    
    public String getTexto() {
        return texto;
    }
}
