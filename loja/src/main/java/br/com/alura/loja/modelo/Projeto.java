package br.com.alura.loja.modelo;

import com.thoughtworks.xstream.XStream;

public class Projeto {

	private String nome;

	private Long id;

	private Integer anoInicio;
	
	public Projeto() {
	}

	public Projeto(Long id,String nome, Integer anoInicio) {
		this.id = id;
		this.nome = nome;
		this.anoInicio = anoInicio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public Integer getAnoInicio() {
		return anoInicio;
	}
	
	public String toXml() {
		return new XStream().toXML(this);	
	}

}
