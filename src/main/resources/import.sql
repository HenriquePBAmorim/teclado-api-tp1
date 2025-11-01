-- Entidade: regiao
INSERT INTO regiao (id, nome) VALUES (1, 'Centro-Oeste');
INSERT INTO regiao (id, nome) VALUES (2, 'Nordeste');
INSERT INTO regiao (id, nome) VALUES (3, 'Norte');
INSERT INTO regiao (id, nome) VALUES (4, 'Sudeste');
INSERT INTO regiao (id, nome) VALUES (5, 'Sul');

-- Entidade: marca
INSERT INTO marca (id, nome, descricao) VALUES (1, 'Logitech', 'Fabricante de periféricos suíça');
INSERT INTO marca (id, nome, descricao) VALUES (2, 'Razer', 'Fabricante focada em gamers');
INSERT INTO marca (id, nome, descricao) VALUES (3, 'HyperX', 'Divisão de periféricos da Kingston');
INSERT INTO marca (id, nome, descricao) VALUES (4, 'Redragon', 'Marca chinesa de periféricos');

-- Entidade: switch
INSERT INTO switch (id, nome, fabricante, tipo, forcaatuacao) VALUES(1, 'Cherry MX Red', 'Cherry', 1, 45.0);
INSERT INTO switch (id, nome, fabricante, tipo, forcaatuacao) VALUES(2, 'Cherry MX Brown', 'Cherry', 2, 55.0);
INSERT INTO switch (id, nome, fabricante, tipo, forcaatuacao) VALUES(3, 'Razer Green', 'Razer', 3, 50.0);
INSERT INTO switch (id, nome, fabricante, tipo, forcaatuacao) VALUES(4, 'Outemu Blue', 'Outemu', 3, 60.0);

-- Entidade: keycap
INSERT INTO keycap (id, nome, material, perfil, cor) VALUES(1, 'Keycap ABS Padrão', 'ABS', 1, 'Preto');
INSERT INTO keycap (id, nome, material, perfil, cor) VALUES(2, 'Keycap PBT Double-Shot', 'PBT', 2, 'Cinza/Branco');

-- Entidade: categoria
INSERT INTO categoria (id, nome, descricao) VALUES(1, 'Gamer', 'Teclados com foco em jogos');
INSERT INTO categoria (id, nome, descricao) VALUES(2, 'Escritório', 'Teclados focados em produtividade');
INSERT INTO categoria (id, nome, descricao) VALUES(3, 'Sem Fio', 'Teclados com conexão Bluetooth ou 2.4Ghz');

-- Entidade: estoque
INSERT INTO estoque (id, quantidade, dataatualizacao) VALUES(1, 100, '2025-10-30');
INSERT INTO estoque (id, quantidade, dataatualizacao) VALUES(2, 50, '2025-10-30');
INSERT INTO estoque (id, quantidade, dataatualizacao) VALUES(3, 75, '2025-10-30');

-- Entidade: estado (Depende de regiao)
INSERT INTO estado (nome, sigla, regiao) VALUES('Tocantins', 'TO', 3);
INSERT INTO estado (nome, sigla, regiao) VALUES('Goiás', 'GO', 1);
INSERT INTO estado (nome, sigla, regiao) VALUES('São Paulo', 'SP', 4);

-- Entidade: modelo (Depende de marca)
INSERT INTO modelo (id, nome, id_marca) VALUES(1, 'G Pro Series', 1);
INSERT INTO modelo (id, nome, id_marca) VALUES(2, 'BlackWidow Series', 2);
INSERT INTO modelo (id, nome, id_marca) VALUES(3, 'Alloy Series', 3);
INSERT INTO modelo (id, nome, id_marca) VALUES(4, 'Redragon K Series', 4);

-- Entidade: produto (Superclasse Abstrata de teclado e acessorio)
INSERT INTO produto (id, nome, preco) VALUES(1, 'G Pro X', 950.00);
INSERT INTO produto (id, nome, preco) VALUES(2, 'BlackWidow V3', 850.00);
INSERT INTO produto (id, nome, preco) VALUES(3, 'Alloy FPS Pro', 600.00);
INSERT INTO produto (id, nome, preco) VALUES(100, 'Cabo USB-C Enrolado', 89.90);
INSERT INTO produto (id, nome, preco) VALUES(101, 'Apoio de Pulso de Madeira', 120.00);


-- Entidade: municipio (Depende de estado)
INSERT INTO municipio (nome, id_estado) VALUES('Palmas', 1);
INSERT INTO municipio (nome, id_estado) VALUES('Araguaína', 1);
INSERT INTO municipio (nome, id_estado) VALUES('Gurupi', 1);
INSERT INTO municipio (nome, id_estado) VALUES('Goiânia', 2);
INSERT INTO municipio (nome, id_estado) VALUES('São Paulo', 3);

-- Entidade: acessorio (Herda de produto)
INSERT INTO acessorio (id, compatibilidade, tipo, material, cor) VALUES(100, 'Universal', 3, 'Nylon', 'Preto');
INSERT INTO acessorio (id, compatibilidade, tipo, material, cor) VALUES(101, 'Teclados 60%', 2, 'Madeira', 'Nogueira');

-- Entidade: teclado (Herda de produto e depende de modelo, switch, keycap, estoque)
INSERT INTO teclado (id, idioma, comfio, iluminacaorgb, datacadastro, tipo, id_modelo, id_switch, id_keycap, id_estoque) 
VALUES (1, 'ANSI', true, true, '2025-10-30', 1, 1, 2, 2, 1);

INSERT INTO teclado (id, idioma, comfio, iluminacaorgb, datacadastro, tipo, id_modelo, id_switch, id_keycap, id_estoque) 
VALUES (2, 'ANSI', true, true, '2025-10-30', 1, 2, 3, 1, 2);

INSERT INTO teclado (id, idioma, comfio, iluminacaorgb, datacadastro, tipo, id_modelo, id_switch, id_keycap, id_estoque) 
VALUES (3, 'ANSI', true, false, '2025-10-30', 1, 3, 1, 1, 3);

-- Tabela de Junção: teclado <-> categoria
INSERT INTO teclado_categoria (id_teclado, id_categoria) VALUES(1, 1);
INSERT INTO teclado_categoria (id_teclado, id_categoria) VALUES(1, 3);
INSERT INTO teclado_categoria (id_teclado, id_categoria) VALUES(2, 1);
INSERT INTO teclado_categoria (id_teclado, id_categoria) VALUES(3, 1);

-- Tabela de Junção: teclado <-> acessorio
INSERT INTO teclado_acessorio (id_teclado, id_acessorio) VALUES(1, 100);
INSERT INTO teclado_acessorio (id_teclado, id_acessorio) VALUES(2, 101);