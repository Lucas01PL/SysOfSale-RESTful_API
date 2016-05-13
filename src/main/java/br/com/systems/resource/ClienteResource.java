package br.com.systems.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.systems.dao.ClienteDao;
import br.com.systems.modelo.Cliente;
import br.com.systems.modelo.Pessoa;

import com.google.gson.Gson;

@Path("clientes")
public class ClienteResource {
	
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public String buscarTodosCliente() {
		List<Pessoa> clientes = new ClienteDao().buscarTodos();
		return new Gson().toJson(clientes);
		
	}
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String buscarClienteId(@PathParam("id") Long id) {
		Cliente cliente = new ClienteDao().buscarId(id);
		return new Gson().toJson(cliente);
		
	}
		
	@Path("/adicionar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)	
	@Produces(MediaType.APPLICATION_JSON)
	public String adicionarCliente(String cliente) {
		if(cliente != null)
		{
			new ClienteDao().salvar(new Gson().fromJson(cliente, Cliente.class));
			return "{\"mensagem\": \"Inclusão realizada com Sucesso!\"}";
		}
		else
		{
			return "{\"mensagem\": \"Falha na Inclusão!\"}";
		}				
	}
	
	@Path("/alterar/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String alterarCliente(@PathParam("id") Long id, Cliente cliente) {
		Cliente clienteConsulta = new ClienteDao().buscarId(id);
		if(clienteConsulta != null)
		{
			new ClienteDao().alterar(cliente);
			return "{\"mensagem\": \"Alteração realizada com Sucesso!\"}";
		}
		else
		{
			return "{\"mensagem\": \"Falha na Alteração!\"}";
		}	
	}
	
	@Path("excluir/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String excluirCliente(@PathParam("id") Long id) {
		Cliente cliente = new ClienteDao().buscarId(id);
		if(cliente != null)
		{
			new ClienteDao().excluir(cliente);
			return "{\"mensagem\": \"Exclusão realizada com Sucesso!\"}";
		}
		else
		{
			return "{\"mensagem\": \"Cliente Inexistente!\"}";
		}				
	}	

}
