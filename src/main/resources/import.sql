-- Marcas
INSERT INTO Marca (id, nome, descricao) VALUES (1, 'Logitech', 'Fabricante de periféricos suíça');
INSERT INTO Marca (id, nome, descricao) VALUES (2, 'Razer', 'Fabricante focada em gamers');
INSERT INTO Marca (id, nome, descricao) VALUES (3, 'HyperX', 'Divisão de periféricos da Kingston');
INSERT INTO Marca (id, nome, descricao) VALUES (4, 'Redragon', 'Marca chinesa de periféricos');

-- Teclados 
INSERT INTO Teclado (id, nome, modelo, tipo, idioma, comFio, iluminacaoRgb, preco, marca_id) 
VALUES (1, 'G Pro X', 'GX Blue', 'Mecânico', 'ANSI', true, true, 950.00, 1);

INSERT INTO Teclado (id, nome, modelo, tipo, idioma, comFio, iluminacaoRgb, preco, marca_id) 
VALUES (2, 'BlackWidow V3', 'Green Switch', 'Mecânico', 'ANSI', true, true, 1200.00, 2);

INSERT INTO Teclado (id, nome, modelo, tipo, idioma, comFio, iluminacaoRgb, preco, marca_id) 
VALUES (3, 'Alloy FPS Pro', 'Red Switch', 'Mecânico', 'ANSI', true, false, 400.00, 3);

INSERT INTO Teclado (id, nome, modelo, tipo, idioma, comFio, iluminacaoRgb, preco, marca_id) 
VALUES (4, 'Kumara K552', 'Outemu Blue', 'Mecânico', 'ABNT2', true, true, 250.00, 4);
