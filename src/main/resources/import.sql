-- REGIÕES (Enum Regiao)
INSERT INTO Regiao (id, nome) VALUES (1, 'Centro-Oeste');
INSERT INTO Regiao (id, nome) VALUES (2, 'Nordeste');
INSERT INTO Regiao (id, nome) VALUES (3, 'Norte');
INSERT INTO Regiao (id, nome) VALUES (4, 'Sudeste');
INSERT INTO Regiao (id, nome) VALUES (5, 'Sul');

-- MARCAS (Entidade Marca)
INSERT INTO Marca (id, nome, descricao) VALUES (1, 'Logitech', 'Fabricante de periféricos suíça');
INSERT INTO Marca (id, nome, descricao) VALUES (2, 'Razer', 'Fabricante focada em gamers');
INSERT INTO Marca (id, nome, descricao) VALUES (3, 'HyperX', 'Divisão de periféricos da Kingston');
INSERT INTO Marca (id, nome, descricao) VALUES (4, 'Redragon', 'Marca chinesa de periféricos');

-- SWITCHES (Entidade Switch)
INSERT INTO Switch (id, nome, fabricante, tipo, forcaAtuacao) VALUES(1, 'Cherry MX Red', 'Cherry', 1, 45.0);
INSERT INTO Switch (id, nome, fabricante, tipo, forcaAtuacao) VALUES(2, 'Cherry MX Brown', 'Cherry', 2, 55.0);
INSERT INTO Switch (id, nome, fabricante, tipo, forcaAtuacao) VALUES(3, 'Razer Green', 'Razer', 3, 50.0);
INSERT INTO Switch (id, nome, fabricante, tipo, forcaAtuacao) VALUES(4, 'Outemu Blue', 'Outemu', 3, 60.0);

-- KEYCAPS (Entidade Keycap)
INSERT INTO Keycap (id, nome, material, perfil, cor) VALUES(1, 'Keycap ABS Padrão', 'ABS', 1, 'Preto');
INSERT INTO Keycap (id, nome, material, perfil, cor) VALUES(2, 'Keycap PBT Double-Shot', 'PBT', 2, 'Cinza/Branco');

-- CATEGORIAS (Entidade Categoria)
INSERT INTO Categoria (id, nome, descricao) VALUES(1, 'Gamer', 'Teclados com foco em jogos');
INSERT INTO Categoria (id, nome, descricao) VALUES(2, 'Escritório', 'Teclados focados em produtividade');
INSERT INTO Categoria (id, nome, descricao) VALUES(3, 'Sem Fio', 'Teclados com conexão Bluetooth ou 2.4Ghz');

-- ESTOQUE (Entidade Estoque)
INSERT INTO Estoque (id, quantidade, dataAtualizacao) VALUES(1, 100, '2025-10-30');
INSERT INTO Estoque (id, quantidade, dataAtualizacao) VALUES(2, 50, '2025-10-30');
INSERT INTO Estoque (id, quantidade, dataAtualizacao) VALUES(3, 75, '2025-10-30');

-- PRODUTOS (para Acessórios)
INSERT INTO Produto (id, nome, preco) VALUES(100, 'Cabo USB-C Enrolado', 89.90);
INSERT INTO Produto (id, nome, preco) VALUES(101, 'Apoio de Pulso de Madeira', 120.00);

-- ACESSORIOS (Entidade Acessorio - depende de Produto)
INSERT INTO Acessorio (id, compatibilidade, tipo, material, cor) VALUES(100, 'Universal', 3, 'Nylon', 'Preto');
INSERT INTO Acessorio (id, compatibilidade, tipo, material, cor) VALUES(101, 'Teclados 60%', 2, 'Madeira', 'Nogueira');

-- 2. DADOS DEPENDENTES (Localização e Modelos)

-- ESTADOS (Depende de Regiao)
INSERT INTO Estado (nome, sigla, regiao) VALUES('Tocantins', 'TO', 3);
INSERT INTO Estado (nome, sigla, regiao) VALUES('Goiás', 'GO', 1);
INSERT INTO Estado (nome, sigla, regiao) VALUES('São Paulo', 'SP', 4);

-- MUNICÍPIOS (Depende de Estado)
INSERT INTO Municipio (nome, id_estado) VALUES('Palmas', 1);
INSERT INTO Municipio (nome, id_estado) VALUES('Araguaína', 1);
INSERT INTO Municipio (nome, id_estado) VALUES('Gurupi', 1);
INSERT INTO Municipio (nome, id_estado) VALUES('Goiânia', 2);
INSERT INTO Municipio (nome, id_estado) VALUES('São Paulo', 3);

-- MODELOS (Depende de Marca)
INSERT INTO Modelo (id, nome, marca_id) VALUES(1, 'G Pro Series', 1);
INSERT INTO Modelo (id, nome, marca_id) VALUES(2, 'BlackWidow Series', 2);
INSERT INTO Modelo (id, nome, marca_id) VALUES(3, 'Alloy Series', 3);
INSERT INTO Modelo (id, nome, marca_id) VALUES(4, 'Redragon K Series', 4);


-- 3. PRODUTOS (para Teclados)
-- (Estes registros na tabela Produto são a "base" dos teclados)
INSERT INTO Produto (id, nome, preco) VALUES(1, 'G Pro X', 950.00);
INSERT INTO Produto (id, nome, preco) VALUES(2, 'BlackWidow V3', 850.00);
INSERT INTO Produto (id, nome, preco) VALUES(3, 'Alloy FPS Pro', 600.00);


-- 4. TECLADOS (A Entidade Principal)
-- (Note que 'nome' e 'preco' NÃO estão aqui, pois estão na tabela Produto)
-- (tipo = 1 é 'Mecânico')
INSERT INTO Teclado (id, idioma, comFio, iluminacaoRgb, dataCadastro, tipo, id_modelo, id_switch, id_keycap, id_estoque) 
VALUES (1, 'ANSI', true, true, '2025-10-30', 1, 1, 2, 2, 1);

INSERT INTO Teclado (id, idioma, comFio, iluminacaoRgb, dataCadastro, tipo, id_modelo, id_switch, id_keycap, id_estoque) 
VALUES (2, 'ANSI', true, true, '2025-10-30', 1, 2, 3, 1, 2);

INSERT INTO Teclado (id, idioma, comFio, iluminacaoRgb, dataCadastro, tipo, id_modelo, id_switch, id_keycap, id_estoque) 
VALUES (3, 'ANSI', true, false, '2025-10-30', 1, 3, 1, 1, 3);


-- 5. TABELAS DE JUNÇÃO (N:M)

-- teclado_categoria (Ligando Teclados e Categorias)
INSERT INTO teclado_categoria (id_teclado, id_categoria) VALUES(1, 1); -- G Pro X é Gamer
INSERT INTO teclado_categoria (id_teclado, id_categoria) VALUES(1, 3); -- G Pro X é Sem Fio
INSERT INTO teclado_categoria (id_teclado, id_categoria) VALUES(2, 1); -- BlackWidow é Gamer
INSERT INTO teclado_categoria (id_teclado, id_categoria) VALUES(3, 1); -- Alloy é Gamer

-- teclado_acessorio (Ligando Teclados e Acessórios)
INSERT INTO teclado_acessorio (id_teclado, id_acessorio) VALUES(1, 100); -- G Pro X inclui um Cabo Enrolado
INSERT INTO teclado_acessorio (id_teclado, id_acessorio) VALUES(2, 101); -- BlackWidow inclui um Apoio de Pulso