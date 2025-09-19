INSERT INTO tb_ongs (nome, email, senha, cidade, telefone) VALUES ('ONG Admin', 'admin@adotepet.com', '$2a$10$VHFNDMgT.Zi5zt26SOumsuhrvWb9cFXrgPUMIcXVmS3CNea86u3hK', 'Cidade Admin', '12345-6789');
INSERT INTO tb_role (id, nome) VALUES (1, 'ROLE_ADMIN');
INSERT INTO tb_role (id, nome) VALUES (2, 'ROLE_ONG');
INSERT INTO tb_ong_roles (ong_id, role_id) VALUES (1, 1);