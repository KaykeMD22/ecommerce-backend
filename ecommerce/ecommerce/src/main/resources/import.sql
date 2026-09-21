insert into categoria (nome, descricao) values ('Livros', 'Livros Técnicos');
insert into categoria (nome, descricao) values ('Eletrônicos', 'Equipamentos Eletrônicos');
insert into categoria (nome, descricao) values ('Escritório', 'Material de Escritório');
insert into categoria (nome, descricao) values ('Informática', 'Produtos de Informática');
insert into categoria (nome, descricao) values ('Acessórios', 'Acessórios Diversos');


insert into produto (nome, descricao, preco, estoque, categoria_id) values ('Código Limpo', 'Livro do Autor Robert Martin', 73.44, 20, 1);
insert into produto (nome, descricao, preco, estoque, categoria_id) values ('Java Efetivo', 'Livro sobre desenvolvimento Java', 89.90, 15, 1);
insert into produto (nome, descricao, preco, estoque, categoria_id) values ('Mouse Sem Fio', 'Mouse óptico sem fio', 59.90, 30, 2);
insert into produto (nome, descricao, preco, estoque, categoria_id) values ('Teclado Mecânico', 'Teclado mecânico USB', 199.90, 10, 2);
insert into produto (nome, descricao, preco, estoque, categoria_id) values ('Caderno Universitário', 'Caderno de 200 folhas', 24.90, 50, 3);


insert into cliente (nome, email, telefone) values ('João Silva', 'joao@email.com', '14999990001');
insert into cliente (nome, email, telefone) values ('Maria Oliveira', 'maria@email.com', '14999990002');
insert into cliente (nome, email, telefone) values ('Pedro Santos', 'pedro@email.com', '14999990003');
insert into cliente (nome, email, telefone) values ('Ana Souza', 'ana@email.com', '14999990004');
insert into cliente (nome, email, telefone) values ('Carlos Lima', 'carlos@email.com', '14999990005');


insert into pedido (data, status, valor_total, cliente_id) values ('2026-09-01 10:00:00', 'PAGO', 163.34, 1);
insert into pedido (data, status, valor_total, cliente_id) values ('2026-09-02 11:30:00', 'PENDENTE', 89.90, 2);
insert into pedido (data, status, valor_total, cliente_id) values ('2026-09-03 14:00:00', 'PAGO', 259.80, 3);
insert into pedido (data, status, valor_total, cliente_id) values ('2026-09-04 16:20:00', 'ENVIADO', 24.90, 4);
insert into pedido (data, status, valor_total, cliente_id) values ('2026-09-05 09:15:00', 'PENDENTE', 59.90, 5);


insert into item_pedido (quantidade, valor_unitario, pedido_id, produto_id) values (1, 73.44, 1, 1);
insert into item_pedido (quantidade, valor_unitario, pedido_id, produto_id) values (1, 89.90, 2, 2);
insert into item_pedido (quantidade, valor_unitario, pedido_id, produto_id) values (2, 129.90, 3, 3);
insert into item_pedido (quantidade, valor_unitario, pedido_id, produto_id) values (1, 24.90, 4, 5);
insert into item_pedido (quantidade, valor_unitario, pedido_id, produto_id) values (1, 59.90, 5, 3);


insert into pagamento (valor, data, status, tipo, pedido_id) values (163.34, '2026-09-01 10:05:00', 'APROVADO', 'PIX', 1);
insert into pagamento (valor, data, status, tipo, pedido_id) values (89.90, '2026-09-02 11:35:00', 'PENDENTE', 'CARTAO', 2);
insert into pagamento (valor, data, status, tipo, pedido_id) values (259.80, '2026-09-03 14:05:00', 'APROVADO', 'CARTAO', 3);
insert into pagamento (valor, data, status, tipo, pedido_id) values (24.90, '2026-09-04 16:25:00', 'APROVADO', 'PIX', 4);
insert into pagamento (valor, data, status, tipo, pedido_id) values (59.90, '2026-09-05 09:20:00', 'PENDENTE', 'PIX', 5);