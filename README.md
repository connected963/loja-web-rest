loja-web-rest: API Rest de Loja Virtual
========================
Projeto educativo contendo uma API REST de Loja Virtual para fins de estudo.

API REST Loja Virtual
-------------

URL Base
-------------
http://lojawebrest-diegokeller.rhcloud.com/rest/

Produtos
-------------

Busca de todos os produtos
GET http://lojawebrest-diegokeller.rhcloud.com/rest/produtos

Busca de produtos por categoria
GET http://lojawebrest-diegokeller.rhcloud.com/rest/produtos/Eletrônicos

Busca de um produto pelo id
GET http://lojawebrest-diegokeller.rhcloud.com/rest/produto/1

Listar as categorias de produtos
GET http://lojawebrest-diegokeller.rhcloud.com/rest/categoria_produto

Avaliações de Produto
-------------

Lista das avaliações de um produto
GET http://lojawebrest-diegokeller.rhcloud.com/rest/avaliacoes/1

Adicionar uma avaliação
PUT http://lojawebrest-diegokeller.rhcloud.com/rest/avaliacao

Usuário
-------------

Buscar usuário por email ou ID
GET http://lojawebrest-diegokeller.rhcloud.com/rest/usuario/keller.diego+joao@gmail.com

Troca de Senha
POST http://lojawebrest-diegokeller.rhcloud.com/rest/trocar_senha
Parâmetros:
* usuarioId
* atual
* nova
* confirmacao

Recuperar Senha
POST http://lojawebrest-diegokeller.rhcloud.com/rest/recuperar_senha
Parâmetros:
* email

Autenticação
POST http://lojawebrest-diegokeller.rhcloud.com/rest/autenticacao
Parâmetros
* email
* senha

Carrinho de Compras
-------------

Adicionar um item ao carrinho
PUT http://lojawebrest-diegokeller.rhcloud.com/rest/carrinho
Parâmetros
* produto
  * id
* usuario
  * id
* quantidade

Atualizar os itens do carrinho
POST http://lojawebrest-diegokeller.rhcloud.com/rest/carrinho

Remover um item do carrinho
DELETE http://lojawebrest-diegokeller.rhcloud.com/rest/carrinho/ID

Pegar um item do carrinho
GET http://lojawebrest-diegokeller.rhcloud.com/rest/carrinho/ID

Pedidos
-------------

Listar os pedidos de um usuário
GET http://lojawebrest-diegokeller.rhcloud.com/rest/pedidos/1

Inserir um pedido
PUT http://lojawebrest-diegokeller.rhcloud.com/rest/pedido

Buscar um pedido pelo id
GET http://lojawebrest-diegokeller.rhcloud.com/rest/pedido/1

Banners
-------------

Obter a lista dos banners
GET http://lojawebrest-diegokeller.rhcloud.com/rest/banners
