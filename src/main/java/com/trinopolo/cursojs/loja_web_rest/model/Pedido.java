package com.trinopolo.cursojs.loja_web_rest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: ItemCarrinho
 *
 */
@Entity
@XmlRootElement(name = "pedido")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pedido implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ItemPedido> itens;

	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	private static final long serialVersionUID = 1L;

	public Pedido() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", itens=" + itens + ", usuario=" + usuario + ", data=" + data + "]";
	}

}
