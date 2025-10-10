-- Insere/Atualiza a ONG Admin. Se a ONG com ID 1 já existir, nada acontece.
MERGE INTO tb_ongs (id, nome, email, senha, cidade, telefone) KEY(id) VALUES (1, 'ONG Admin', 'admin@adotepet.com', '$2a$10$3gE.E.q.T8qA.j2.s4g0/e.l/s8.w5P9j5B/8yE.e2fF.e1Z.w9K', 'Cidade Admin', '12345-6789');

-- Insere/Atualiza os papéis.
MERGE INTO tb_role (id, nome) KEY(id) VALUES (1, 'ROLE_ADMIN');
MERGE INTO tb_role (id, nome) KEY(id) VALUES (2, 'ROLE_ONG');

-- Insere/Atualiza a associação de papel para a ONG Admin.
MERGE INTO tb_ong_roles (ong_id, role_id) KEY(ong_id, role_id) VALUES (1, 1);