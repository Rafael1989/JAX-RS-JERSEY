package br.com.alura.loja.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
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
	
	public String toJson() {
		return new Gson().toJson(this);
	}

}
