INSERT INTO papeis (id, nome_papel)
VALUES (1, 'ADMIN');

INSERT INTO papeis (id, nome_papel)
VALUES (2, 'ALUNO');

INSERT INTO papeis (id, nome_papel)
VALUES (3, 'PROFESSOR');

INSERT INTO papeis (id, nome_papel)
VALUES (4, 'ALUNO');

INSERT INTO cursos (id, nome)
VALUES (1, 'Curso A');

INSERT INTO cursos (id, nome)
VALUES (2, 'Curso B');

INSERT INTO usuarios (id, login, senha, role, papel_id)
VALUES (1, 'admin', '$2a$12$XOOpwCeFKNnelqXt/mfF4e2bUfZbOLns0P2oHy8R.OrgedboiWyU.', 0, 1);

INSERT INTO usuarios (id, login, senha, role, papel_id)
VALUES (2, 'aluno', '$2a$12$XOOpwCeFKNnelqXt/mfF4e2bUfZbOLns0P2oHy8R.OrgedboiWyU.', 4, 2);

INSERT INTO usuarios (id, login, senha, role, papel_id)
VALUES (3, 'professor', '$2a$12$XOOpwCeFKNnelqXt/mfF4e2bUfZbOLns0P2oHy8R.OrgedboiWyU.', 3, 3);

INSERT INTO usuarios (id, login, senha, role, papel_id)
VALUES (4, 'aluno2', '$2a$12$XOOpwCeFKNnelqXt/mfF4e2bUfZbOLns0P2oHy8R.OrgedboiWyU.', 4, 4);

INSERT INTO docentes (id, data_entrada, nome, data_nascimento, cep, complemento, referencia, numero, rg, telefone, naturalidade, cpf, estado_civil, email, genero, usuario_id)
VALUES (1, '1990-10-10', 'Paulo César', '1970-10-10', '88040030', 'apto 204', 'predio verde', '1000', '22222222', '(48)9 6666-0000', 'Florianópolis', '000.000.000-00', 'Casado(a)', 'luiz@docente.com', 'Masculino', 2);

INSERT INTO docentes (id, data_entrada, nome, data_nascimento, cep, complemento, referencia, numero, rg, telefone, naturalidade, cpf, estado_civil, email, genero, usuario_id)
VALUES (2, '1990-10-10', 'Luiz Otávio', '1970-10-10', '88040030', 'casa', 'prox. à farmácia', '1000', '99999999', '(48)9 6666-0000', 'Florianópolis', '000.000.000-00', 'Casado(a)', 'paulo@docente.com', 'Masculino', 3);

INSERT INTO turmas (id, nome , curso_id , professor_id)
VALUES (1, 'Turma 01', 1, 1);

INSERT INTO alunos (id, nome, data_nascimento, cep, complemento, referencia, numero, rg, telefone, naturalidade, cpf, estado_civil, email, genero, turma_id , usuario_id)
VALUES (1, 'Sergio Malandro', '1980-10-10', '88040030', 'casa', 'rua da padaria', '160', '8888888', '(48)9 7777-2221', 'Florianópolis', '000.000.000-00', 'Solteiro(a)', 'sergio@aluno.com', 'Masculino', 1, 2);

INSERT INTO alunos (id, nome, data_nascimento, cep, complemento, referencia, numero, rg, telefone, naturalidade, cpf, estado_civil, email, genero, turma_id , usuario_id)
VALUES (2, 'Maurício de Souza', '1990-10-10', '88040030', 'casa', 'rua do mercado','150', '99999999', '(48)9 9999-9999', 'Florianópolis', '000.000.000-00', 'Casado(a)', 'mauricio@aluno.com', 'Masculino', 1, 4);

INSERT INTO materias (id, nome , curso_id)
VALUES (1, 'Matemática', 1);

INSERT INTO materias (id, nome , curso_id)
VALUES (2, 'Física', 1);

INSERT INTO materias (id, nome , curso_id)
VALUES (3, 'Português', 2);

INSERT INTO notas (id, aluno_id , data , docente_id , materia_id, valor)
VALUES (1, 1, '2024-10-10', 1, 2, 10.0);

INSERT INTO notas (id, aluno_id , data , docente_id , materia_id, valor)
VALUES (2, 1, '2024-10-10', 1, 2, 9.0);

INSERT INTO notas (id, aluno_id , data , docente_id , materia_id, valor)
VALUES (3, 1, '2024-10-10', 1, 2, 9.5);