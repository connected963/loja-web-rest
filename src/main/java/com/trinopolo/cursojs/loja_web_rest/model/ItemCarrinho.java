package com.trinopolo.cursojs.loja_web_rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: ItemCarrinho
 *
 */
@Entity

public class ItemCarrinho implements Serializable {

	@Id
	private Integer id;

	private Integer quantidade;

	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	private Produto produto;

	private static final long serialVersionUID = 1L;

	public ItemCarrinho() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "ItemCarrinho [id=" + id + ", quantidade=" + quantidade + ", usuario=" + usuario + ", produto=" + produto + "]";
	}

}
