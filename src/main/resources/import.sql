-- src/main/resources/import.sql
-- (código SQL de inserção de dados)
-- ...
-- REGIÕES
INSERT INTO Regiao (id, nome) VALUES (1, 'Centro-Oeste');
INSERT INTO Regiao (id, nome) VALUES (2, 'Nordeste');
INSERT INTO Regiao (id, nome) VALUES (3, 'Norte');
INSERT INTO Regiao (id, nome) VALUES (4, 'Sudeste');
INSERT INTO Regiao (id, nome) VALUES (5, 'Sul');

-- ESTADOS
INSERT INTO Estado (nome, sigla, regiao) VALUES('Tocantins', 'TO', 3);
INSERT INTO Estado (nome, sigla, regiao) VALUES('Goiás', 'GO', 1);
INSERT INTO Estado (nome, sigla, regiao) VALUES('São Paulo', 'SP', 4);

-- MUNICÍPIOS (ATUALIZADO)
INSERT INTO Municipio (nome, id_estado) VALUES('Palmas', 1);
INSERT INTO Municipio (nome, id_estado) VALUES('Araguaína', 1);
INSERT INTO Municipio (nome, id_estado) VALUES('Gurupi', 1);
INSERT INTO Municipio (nome, id_estado) VALUES('Goiânia', 2);
INSERT INTO Municipio (nome, id_estado) VALUES('São Paulo', 3);

-- MARCAS
INSERT INTO Marca (id, nome, descricao) VALUES (1, 'Logitech', 'Fabricante de periféricos suíça');
INSERT INTO Marca (id, nome, descricao) VALUES (2, 'Razer', 'Fabricante focada em gamers');
INSERT INTO Marca (id, nome, descricao) VALUES (3, 'HyperX', 'Divisão de periféricos da Kingston');
INSERT INTO Marca (id, nome, descricao) VALUES (4, 'Redragon', 'Marca chinesa de periféricos');


-- Teclados 
INSERT INTO Teclado (id, nome, modelo, tipo, idioma, comFio, iluminacaoRgb, preco, marca_id) 
VALUES (1, 'G Pro X', 'GX Blue', 1, 'ANSI', true, true, 950.00, 1); 
INSERT INTO Teclado (id, nome, modelo, tipo, idioma, comFio, iluminacaoRgb, preco, marca_id) 
VALUES (2, 'BlackWidow V3', 'Green Switch', 1, 'ANSI', true, true, 850.00, 2); 
INSERT INTO Teclado (id, nome, modelo, tipo, idioma, comFio, iluminacaoRgb, preco, marca_id) 
VALUES (3, 'Alloy FPS Pro', 'Red Switch', 1, 'ANSI', true, false, 600.00, 3); 
