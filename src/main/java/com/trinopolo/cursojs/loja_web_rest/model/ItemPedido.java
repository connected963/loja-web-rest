package com.trinopolo.cursojs.loja_web_rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * Entity implementation class for Entity: ItemCarrinho
 *
 */
@Entity
public class ItemPedido implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer quantidade;

	@ManyToOne
	private Produto produto;

	@Transient
	private Usuario usuario;

	private static final long serialVersionUID = 1L;

	public ItemPedido() {
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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "ItemCarrinho [id=" + id + ", quantidade=" + quantidade + ", produto=" + produto + "]";
	}

}
