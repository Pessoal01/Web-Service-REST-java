package br.com.restful.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.restful.controller.ClienteController;
import br.com.restful.model.Cliente;

/**
 * Classe respons�vel por conter os metodos REST de acesso ao webservice
 * 
 * @author motos
 *
 */
@Path("/cliente")
public class ClienteResource {

	/**
	*  M�todo respons�vel por fazer chamada ao controller
	* @return ArrayList<Cliente>
	* @author Manoel Silva Motoso <manoelmotoso@hotmail.com>
	* @since 12/05/2016 12:05:44
	* @version 1.0
	*/
	@GET
	@Path("/listarTodos")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Cliente> listarTodos(){
		System.out.println("Clientes encontrados no banco");
		return new ClienteController().listarTodos();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id/{id}")
	public Response getById(@PathParam("id") Long id) {
		Cliente cliente = new ClienteController().buscarPorId(id);
		if(cliente != null){
				return Response
						.ok()	
						.type(MediaType.APPLICATION_JSON)
						.entity(cliente)
						.build();
		}else{
		return Response
				.status(404)
				.entity("Cliente n�o encontrado")
				.build();
		}
	}
	

	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/salvar")
	public Response salvarClienteJson(Cliente cliente){
		boolean isClienteGravado = new ClienteController().gravarCliente(cliente);
		if(isClienteGravado == true){		
		return Response
						.ok()
						.entity(cliente)
						.build();
		}else{
			return Response
					.status(500)
					.entity("Erro no servidor  ao gravar cliente")
					.build();
		}
		
	}

}