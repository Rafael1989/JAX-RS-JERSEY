package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.Projeto;
import junit.framework.Assert;

public class ProjetoTest {
	
	private HttpServer server;
	
	@Before
	public void startaServidor() {
		this.server = Servidor.inicializaServidor();
	}
	
	@After
	public void mataServidor() {
		this.server.stop();
	}
	
	@Test
	public void testaQueAConexaoComOServidorFunciona() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/");
		Projeto projeto = target.path("projetos/1").request().get(Projeto.class);
		//Projeto projeto = (Projeto) new XStream().fromXML(response); XStream
		Assert.assertEquals("Minha loja", projeto.getNome());
	}
	
	@Test
	public void testaAdicaoCarrinho() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		Projeto projeto = new Projeto(1L,"Previdência",2018);
		//String xml = projeto.toXml();
		Entity<Projeto> entity = Entity.entity(projeto, MediaType.APPLICATION_XML);
		Response response = target.path("/projetos").request().post(entity);
		Assert.assertEquals(201, response.getStatus());
		String location = response.getHeaderString("Location");
		Projeto projetoEncontrado = client.target(location).request().get(Projeto.class);
		Assert.assertTrue(projetoEncontrado.getNome().contains("Previdência"));
	}


}
