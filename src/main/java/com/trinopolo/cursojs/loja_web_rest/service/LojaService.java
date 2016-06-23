/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.trinopolo.cursojs.loja_web_rest.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.trinopolo.cursojs.loja_web_rest.model.Avaliacao;
import com.trinopolo.cursojs.loja_web_rest.model.ItemCarrinho;
import com.trinopolo.cursojs.loja_web_rest.model.Pedido;
import com.trinopolo.cursojs.loja_web_rest.model.Usuario;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class LojaService {

	@Inject
	private EntityManager em;

	public void salvarUsuario(Usuario usuario) {
		em.merge(usuario);
	}

	public void salvarAvaliacao(Avaliacao avaliacao) throws Exception {
		if (avaliacao.getUsuario() == null) {
			throw new Exception("Usuário é obrigatório");
		}
		if (avaliacao.getNota() == null) {
			throw new Exception("Nota é obrigatório");
		}
		if (avaliacao.getTitulo() == null) {
			throw new Exception("Título é obrigatório");
		}
		if (avaliacao.getDescricao() == null) {
			throw new Exception("Descrição é obrigatório");
		}

		avaliacao.setId(null);
		avaliacao.setData(new Date());
		em.merge(avaliacao);
	}

	public void adicionarItemCarrinho(ItemCarrinho item) throws Exception {
		if (item.getUsuario() == null) {
			throw new Exception("Usuário é obrigatório");
		}
		if (item.getProduto() == null) {
			throw new Exception("Nota é obrigatório");
		}
		if (item.getQuantidade() == null) {
			item.setQuantidade(1);
		}

		item.setId(null);
		em.merge(item);
	}

	public void atualizarItemCarrinho(ItemCarrinho item) throws Exception {
		em.merge(item);
	}

	public void removerItemCarrinho(Integer id) throws Exception {
		em.remove(em.find(ItemCarrinho.class, id));
	}

	public List<ItemCarrinho> buscarItensCarrinhoPorUsuario(Integer id) {
		TypedQuery<ItemCarrinho> query = em.createQuery("select e from ItemCarrinho as e where e.usuario.id = :id", ItemCarrinho.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

	public void salvarPedido(Pedido pedido) throws Exception {

		if (pedido.getUsuario() == null || pedido.getUsuario().getId() == null) {
			throw new Exception("Campo usuário é obrigatório");
		}

		pedido.setId(null);
		pedido.setData(new Date());

		pedido.getItens().forEach(item -> {
			item.setId(null);
		});

		em.merge(pedido);

		List<ItemCarrinho> itens = buscarItensCarrinhoPorUsuario(pedido.getUsuario().getId());
		for (ItemCarrinho itemCarrinho : itens) {
			em.remove(itemCarrinho);
		}
	}
}
