INSERT INTO papeis (nome_papel)
VALUES ('ADMIN');

INSERT INTO papeis (nome_papel)
VALUES ('ALUNO');

INSERT INTO papeis (nome_papel)
VALUES ('PROFESSOR');

INSERT INTO papeis (nome_papel)
VALUES ('ALUNO');

INSERT INTO cursos (nome)
VALUES ('Curso A');

INSERT INTO cursos (nome)
VALUES ('Curso B');

INSERT INTO cursos (nome)
VALUES ('Curso C');

INSERT INTO cursos (nome)
VALUES ('Curso D');

INSERT INTO usuarios (login, senha, role, papel_id)
VALUES ('admin', '$2a$12$XOOpwCeFKNnelqXt/mfF4e2bUfZbOLns0P2oHy8R.OrgedboiWyU.', 0, 1);

INSERT INTO usuarios (login, senha, role, papel_id)
VALUES ('aluno', '$2a$12$XOOpwCeFKNnelqXt/mfF4e2bUfZbOLns0P2oHy8R.OrgedboiWyU.', 4, 2);

INSERT INTO usuarios (login, senha, role, papel_id)
VALUES ('professor', '$2a$12$XOOpwCeFKNnelqXt/mfF4e2bUfZbOLns0P2oHy8R.OrgedboiWyU.', 3, 3);

INSERT INTO usuarios (login, senha, role, papel_id)
VALUES ('aluno2', '$2a$12$XOOpwCeFKNnelqXt/mfF4e2bUfZbOLns0P2oHy8R.OrgedboiWyU.', 4, 4);

INSERT INTO docentes (nome, data_nascimento, cep, complemento, referencia, numero, rg, telefone, naturalidade, cpf, estado_civil, email, genero, usuario_id)
VALUES ('Paulo César', '1970-10-10', '88040030', 'apto 204', 'predio verde', '1000', '22222222', '(48)9 6666-0000', 'Florianópolis', '000.000.000-00', 'Casado(a)', 'luiz@docente.com', 'Masculino', 2);

INSERT INTO docentes (nome, data_nascimento, cep, complemento, referencia, numero, rg, telefone, naturalidade, cpf, estado_civil, email, genero, usuario_id)
VALUES ('Luiz Otávio', '1970-10-10', '88040030', 'casa', 'prox. à farmácia', '1000', '99999999', '(48)9 6666-0000', 'Florianópolis', '000.000.000-00', 'Casado(a)', 'paulo@docente.com', 'Masculino', 3);

INSERT INTO materias (nome , curso_id)
VALUES ('Matemática', 1);

INSERT INTO materias (nome , curso_id)
VALUES ('Física', 1);

INSERT INTO materias (nome , curso_id)
VALUES ('Português', 2);

INSERT INTO turmas (nome , curso_id , professor_id, materia_id)
VALUES ('Turma 01', 1, 1, 1);

INSERT INTO turmas (nome , curso_id , professor_id, materia_id)
VALUES ('Turma 02', 1, 1, 2);

INSERT INTO turmas (nome , curso_id , professor_id, materia_id)
VALUES ('Turma 03', 2, 2, 3);

INSERT INTO alunos (nome, data_nascimento, cep, estado, bairro, logradouro, cidade, complemento, referencia, numero, rg, telefone, naturalidade, cpf, estado_civil, email, genero, turma_id , usuario_id)
VALUES ('Sergio Malandro', '1990-10-10', '88040030', 'SC', 'Pantanal', 'Rua José Bernardes Vieira', 'Florianopolis','casa', 'rua do mercado','1212', '77777777', '(48)9 9999-9999', 'Florianópolis', '000.000.000-00', 'Solteiro(a)', 'sergio@aluno.com', 'Masculino', 1, 2);

INSERT INTO alunos (nome, data_nascimento, cep, estado, bairro, logradouro, cidade, complemento, referencia, numero, rg, telefone, naturalidade, cpf, estado_civil, email, genero, turma_id , usuario_id)
VALUES ('Maurício de Souza', '1990-10-10', '88040030', 'SC', 'Pantanal', 'Rua José Bernardes Vieira', 'Florianopolis','casa', 'rua do mercado','150', '99999999', '(48)9 9999-9999', 'Florianópolis', '000.000.000-00', 'Casado(a)', 'mauricio@aluno.com', 'Masculino', 1, 4);

INSERT INTO notas (aluno_id , data_nota , docente_id , materia_id, valor, data_termino, horario, nome_avaliacao, turma_id)
VALUES (1, '2024-09-07', 1, 1, 9.0, '2024-10-10', '08:20', 'Prova 1', 1);

INSERT INTO notas (aluno_id , data_nota , docente_id , materia_id, valor, data_termino, horario, nome_avaliacao, turma_id)
VALUES (1, '2024-10-15', 1, 2, 9.0, '2024-10-10', '08:20', 'Prova 2', 1);

INSERT INTO notas (aluno_id , data_nota , docente_id , materia_id, valor, data_termino, horario, nome_avaliacao, turma_id)
VALUES (1, '2024-10-20', 1, 1, 9.0, '2024-10-10', '08:20', 'Trabalho 1', 1);

INSERT INTO notas (aluno_id , data_nota , docente_id , materia_id, valor, data_termino, horario, nome_avaliacao, turma_id)
VALUES (2, '2024-10-10', 1, 2, 9.0, '2024-10-10', '08:20', 'Prova 1', 1);


