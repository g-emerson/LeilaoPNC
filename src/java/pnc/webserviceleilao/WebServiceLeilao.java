/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.webserviceleilao;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import pnc.leilaoVerde.dominio.entidades.Entidade;

/**
 *
 * @author Vinicius
 */
@WebService(serviceName = "WebServiceLeilao")
public class WebServiceLeilao {

	/**
	 * Operação de Web service
	 */
	@WebMethod(operationName = "getEntidade")
	public Entidade getEntidade(@WebParam(name = "id") String id) {
		Entidade entidade = Entidade.getPeloCnpj(id);

		return entidade;
	}
}
