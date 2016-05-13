package br.com.systems;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class ClienteTest {
	
	public static void main(String[] args) {
		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target("http://localhost:8081/SysOfSale-RESTful_API/servicos");		
		String resultado = target.path("/clientes/teste").request().get(String.class);
		
	}

}
