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

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import com.trinopolo.cursojs.loja_web_rest.model.Pedido;
import com.trinopolo.cursojs.loja_web_rest.service.LojaService;

@Path("/pedido")
@RequestScoped
public class PedidoRESTService {

	@Inject
	EntityManager em;

	@Inject
	LojaService lojaService;

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response salvar(Pedido pedido) {
		try {
			lojaService.salvarPedido(pedido);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", "Erro ao salvar o pedido: " + e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseObj).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Pedido buscar(@PathParam("id") Integer id) {
		return em.find(Pedido.class, id);
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}/xml")
	public Response downloadXML(@PathParam("id") Integer id) {
		Pedido pedido = em.find(Pedido.class, id);
		try {
			JAXBContext context = JAXBContext.newInstance("com.trinopolo.cursojs.loja_web_rest.model");
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			m.marshal(pedido, baos);

			ResponseBuilder response = Response.ok(baos.toByteArray(), MediaType.APPLICATION_XML);
			response.header("Content-Disposition", "attachment; filename=pedido-" + id + ".xml");
			return response.build();
		} catch (PropertyException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.OK).build();
	}

}
