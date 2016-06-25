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
package com.trinopolo.cursojs.loja_web_rest.rest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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

import com.trinopolo.cursojs.loja_web_rest.model.ItemCarrinho;
import com.trinopolo.cursojs.loja_web_rest.service.LojaService;

@Path("/carrinho")
@RequestScoped
public class CarrinhoRESTService {

	@Inject
	EntityManager em;

	@Inject
	LojaService lojaService;

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response adicionar(ItemCarrinho item) {
		try {
			lojaService.adicionarItemCarrinho(item);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", "Erro ao adicionar o item no carrinho: " + e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseObj).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response atualizar(List<ItemCarrinho> itens) {
		try {
			for (ItemCarrinho item : itens) {
				lojaService.atualizarItemCarrinho(item);
			}
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", "Erro ao atualizar o item no carrinho: " + e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseObj).build();
		}
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/{id}")
	public Response remover(@PathParam("id") Integer id) {
		try {
			lojaService.removerItemCarrinho(id);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", "Erro ao remover o item no carrinho: " + e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseObj).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public List<ItemCarrinho> buscar(@PathParam("id") Integer id) {
		try {
			return lojaService.buscarItensCarrinhoPorUsuario(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new LinkedList<>();
		}
	}

}
