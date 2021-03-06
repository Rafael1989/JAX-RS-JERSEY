package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

@Path("carrinhos")
public class CarrinhoResource {
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Carrinho busca(@PathParam("id") Long id) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		//return carrinho.toXML();
		return carrinho;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(Carrinho carrinho) {
		//Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo); XStream
		new CarrinhoDAO().adiciona(carrinho);
		URI uri = URI.create("/carrinhos/"+carrinho.getId());
		return Response.created(uri).build();
	}
	
	@DELETE
	@Path("{id}/produtos/{produtoId}")
	public Response removeProduto(@PathParam("id") Long id, @PathParam("produtoId") Long produtoId) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(produtoId);
		return Response.ok().build();
	}
	
	@PUT
	@Path("{id}/produtos/{produtoId}/quantidade")
	@Consumes(MediaType.APPLICATION_XML)
	public Response alteraProduto(@PathParam("id") Long id, @PathParam("produtoId") Long produtoId, Produto produto) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		//Produto produto = (Produto) new XStream().fromXML(conteudo); XStream
		carrinho.trocaQuantidade(produto);
		return Response.ok().build();
	}
}
